package io.github.danielsbaumann.rest.controller;

import io.github.danielsbaumann.domain.entity.Pedido;
import io.github.danielsbaumann.rest.dto.PedidoDTO;
import io.github.danielsbaumann.service.PedidoService;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.HttpStatus.*;


@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

}
