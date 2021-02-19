package io.github.danielsbaumann.service;

import io.github.danielsbaumann.domain.entity.Pedido;
import io.github.danielsbaumann.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obeterPedidoCompleto(Integer id);
}
