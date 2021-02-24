package io.github.danielsbaumann.rest.controller;


import io.github.danielsbaumann.domain.entity.Cliente;
import io.github.danielsbaumann.domain.repository.ClientesTry;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

//@Controller
//@RequestMapping("/api/clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClientesTry clientes;

    public ClienteController(ClientesTry clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/id/{id}")
    public Cliente getClienteById(@PathVariable("id") Integer id) {
        return clientes
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody @Valid Cliente cliente) {
        return clientes.save(cliente);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        clientes.findById(id)
                .map(cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Integer id, @RequestBody @Valid Cliente cliente) {
        clientes
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @GetMapping("/clientes")
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example example = Example.of(filtro);
        //List<Cliente> lista = clientes.findAll(example);
        return clientes.findAll(example);
    }

    //@ResponseBody
//    @GetMapping("/id/{id}")
//    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id) {
//        Optional<Cliente> cliente = clientes.findById(id);
//        if (cliente.isPresent()) {
//            return ResponseEntity.ok(cliente.get());
//        }
//        return ResponseEntity.notFound().build();
//    }

    //@ResponseBody
//    @PostMapping("/save")
//    public ResponseEntity save(@RequestBody Cliente cliente) {
//        Cliente clienteSalvo = clientes.save(cliente);
//        return ResponseEntity.ok(clienteSalvo);
//    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity delete(@PathVariable("id") Integer id) {
//        Optional<Cliente> cliente = clientes.findById(id);
//        if (cliente.isPresent()) {
//            clientes.delete(cliente.get());
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }

    //@ResponseBody
//    @PutMapping("/update/{id}")
//    public ResponseEntity update(
//            @PathVariable Integer id,
//            @RequestBody Cliente cliente
//    ) {
//        return clientes
//                .findById(id)
//                .map(clienteExistente -> {
//                    cliente.setId(clienteExistente.getId());
//                    clientes.save(cliente);
//                    return ResponseEntity.noContent().build();
//                }).orElseGet(() -> ResponseEntity.notFound().build());
//    }

//    @GetMapping("/clientes")
//    public ResponseEntity find(Cliente filtro) {
//        ExampleMatcher matcher = ExampleMatcher
//                .matching()
//                .withIgnoreCase()
//                .withStringMatcher(
//                        ExampleMatcher.StringMatcher.CONTAINING
//                );
//        Example example = Example.of(filtro);
//        List<Cliente> lista = clientes.findAll(example);
//        return ResponseEntity.ok(lista);
//    }

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
