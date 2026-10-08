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
import com.example.baozistore.model.Cliente;
import com.example.baozistore.service.ClienteService;
import jakarta.validation.Valid;

@RestController // Faz Responder em JSON
@RequestMapping("/clientes") // Prefixo das Rotas Deste Controller
public class ClienteController {

	@Autowired
    private ClienteService service;

    @PostMapping // /clientes -> criar
    @ResponseStatus(HttpStatus.CREATED) // 201
    public Cliente criar(@Valid @RequestBody Cliente cliente) {
        return service.criar(cliente);
    }

    @GetMapping // /clientes -> listar todos
    public List<Cliente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}") // /clientes/1 -> consultar por ID
    public Cliente buscarPorId(@PathVariable("id") Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}") // /clientes/1 -> atualizar
    public Cliente atualizar(@PathVariable("id") Long id, @Valid @RequestBody Cliente cliente) {
        return service.atualizar(id, cliente);
    }

    @DeleteMapping("/{id}") // /clientes/1 -> apagar
    public Cliente excluir(@PathVariable("id") Long id) {
        return service.excluir(id);
    }
}
