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
import com.example.baozistore.dto.PedidoRequest;
import com.example.baozistore.model.Pedido;
import com.example.baozistore.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
    private PedidoService service;

    @PostMapping // Recebe do Cliente (clienteId, produtoId e quantidade)
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar(@Valid @RequestBody PedidoRequest dados) {
        return service.criar(dados);
    }

    @GetMapping
    public List<Pedido> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Pedido atualizar(@PathVariable("id") Long id, @Valid @RequestBody PedidoRequest dados) {
        return service.atualizar(id, dados);
    }

    @DeleteMapping("/{id}")
    public Pedido excluir(@PathVariable("id") Long id) {
        return service.excluir(id);
    }
}
