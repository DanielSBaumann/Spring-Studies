package io.github.danielsbaumann.rest.controller;


import io.github.danielsbaumann.domain.entity.Cliente;
import io.github.danielsbaumann.domain.repository.ClientesTry;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesTry clientes;

    public ClienteController(ClientesTry clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) {
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        Optional<Cliente> cliente = clientes.findById(id);
        if (cliente.isPresent()) {
            clientes.delete(cliente.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity update(
            @PathVariable Integer id,
            @RequestBody Cliente cliente
    ) {
        return clientes
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/clientes")
    public ResponseEntity find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example example = Example.of(filtro);
        List<Cliente> lista = clientes.findAll(example);
        return ResponseEntity.ok(lista);
    }

    //teste
//    @RequestMapping(
//            value = {"/hello/{name}", "/qualfoi/{name}"},
//            method = RequestMethod.GET,
//            consumes = {"application/json", "application/xml"},
//            produces = {"application/json", "application/xml"}
//    )
//    @ResponseBody
//    public String helloClientes(@PathVariable("name") String nomeCliente, @RequestBody Cliente cliente) {
//        System.out.println(nomeCliente);
//        return String.format("Hello %s", nomeCliente);
//    }

}
