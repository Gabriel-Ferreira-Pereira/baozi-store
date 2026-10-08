package com.example.baozistore.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.baozistore.model.Produto;
import com.example.baozistore.repository.PedidoRepository;
import com.example.baozistore.repository.ProdutoRepository;

@Service
@Transactional
public class ProdutoService {

	@Autowired
    private ProdutoRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Produto criar(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listar() {
        return repository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado: " + id));
    }

    public Produto atualizar(Long id, Produto dados) {
        Produto produto = buscarPorId(id);
        produto.setNome(dados.getNome());
        produto.setPreco(dados.getPreco());
        produto.setEstoque(dados.getEstoque());
        return repository.save(produto);
    }

    public Produto excluir(Long id) {
        Produto produto = buscarPorId(id);
        if (pedidoRepository.existsByProdutoId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Produto possui pedidos e não pode ser excluído");
        }
        repository.delete(produto);
        return produto;
    }
}
