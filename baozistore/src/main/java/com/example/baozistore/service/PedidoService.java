package com.example.baozistore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.baozistore.dto.PedidoRequest;
import com.example.baozistore.model.Cliente;
import com.example.baozistore.model.Pedido;
import com.example.baozistore.model.Produto;
import com.example.baozistore.repository.PedidoRepository;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    public Pedido criar(PedidoRequest dados) {
        Pedido pedido = montar(new Pedido(), dados);
        return repository.save(pedido);
    }

    public List<Pedido> listar() {
        return repository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado: " + id));
    }

    public Pedido atualizar(Long id, PedidoRequest dados) {
        Pedido pedido = montar(buscarPorId(id), dados);
        return repository.save(pedido);
    }

    public Pedido excluir(Long id) {
        Pedido pedido = buscarPorId(id);
        repository.delete(pedido);
        return pedido;
    }

    // Busca o Cliente e o Produto (da 404 se não existirem) e Valida o Estoque
    private Pedido montar(Pedido pedido, PedidoRequest dados) {
        Cliente cliente = clienteService.buscarPorId(dados.clienteId());
        Produto produto = produtoService.buscarPorId(dados.produtoId());
        if (!Boolean.TRUE.equals(produto.getEstoque())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Produto sem estoque: " + produto.getNome());
        }
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedido.setQuantidade(dados.quantidade());
        return pedido;
    }
}