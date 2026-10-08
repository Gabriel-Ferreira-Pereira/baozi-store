package com.example.baozistore.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.baozistore.model.Produto;
import com.example.baozistore.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
    private ProdutoService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criar(@Valid @RequestBody Produto produto) {
        return service.criar(produto);
    }

    @GetMapping
    public List<Produto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable("id") Long id, @Valid @RequestBody Produto produto) {
        return service.atualizar(id, produto);
    }

    @DeleteMapping("/{id}")
    public Produto excluir(@PathVariable("id") Long id) {
        return service.excluir(id);
    }
}
