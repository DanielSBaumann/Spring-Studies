package io.github.danielsbaumann.service.impl;

import io.github.danielsbaumann.domain.entity.Cliente;
import io.github.danielsbaumann.domain.entity.ItemPedido;
import io.github.danielsbaumann.domain.entity.Pedido;
import io.github.danielsbaumann.domain.entity.Produto;
import io.github.danielsbaumann.domain.enums.StatusPedido;
import io.github.danielsbaumann.domain.repository.ClientesTry;
import io.github.danielsbaumann.domain.repository.ItemPedidos;
import io.github.danielsbaumann.domain.repository.Pedidos;
import io.github.danielsbaumann.domain.repository.Produtos;
import io.github.danielsbaumann.exception.RegraNegocioException;
import io.github.danielsbaumann.rest.dto.ItemPedidoDTO;
import io.github.danielsbaumann.rest.dto.PedidoDTO;
import io.github.danielsbaumann.service.PedidoService;
import io.github.danielsbaumann.exception.PedidoNaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final ClientesTry clientesRepository;
    private final Produtos produtosRepository;
    private final ItemPedidos itemsPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {

        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Codigo de cliente invalido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itensPedidos = converterItems(pedido, dto.getItens());

        repository.save(pedido);
        itemsPedidoRepository.saveAll(itensPedidos);
        pedido.setItens(itensPedidos);
        return pedido;
    }

    @Override
    public Optional<Pedido> obeterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizarStatus(Integer id, StatusPedido statusPedido) {
        repository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return repository.save(pedido);
                })
                .orElseThrow(() -> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itens) {
        if (itens.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens");
        }
        return itens
                .stream()
                .map(dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Codigo de produto inválido: " + idProduto));
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

}
