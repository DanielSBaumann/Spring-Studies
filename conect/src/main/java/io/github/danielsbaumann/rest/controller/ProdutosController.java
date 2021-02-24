package io.github.danielsbaumann.rest.controller;

import io.github.danielsbaumann.domain.entity.Produto;
import io.github.danielsbaumann.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutosController {

    private Produtos repository;

    public ProdutosController(Produtos repository) {
        this.repository = repository;
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(OK)
    public Produto produtosById(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
    }

    @PostMapping("/save")
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody @Valid Produto produto) {
        return repository.save(produto);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        repository.findById(id)
                .map(produto -> {
                    repository.deleteById(id);
                    return Void.TYPE;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado"));
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable("id") Integer id, @RequestBody @Valid Produto produto) {
        repository
                .findById(id)
                .map(p -> {
                    produto.setId(p.getId());
                    repository.save(produto);
                    return produto;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado"));
    }

    @GetMapping("/produtos")
    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example example = Example.of(filtro);
        return repository.findAll(example);
    }

}















