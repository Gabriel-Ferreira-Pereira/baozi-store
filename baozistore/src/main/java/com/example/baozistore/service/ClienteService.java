package com.example.baozistore.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.example.baozistore.model.Cliente;
import com.example.baozistore.repository.ClienteRepository;
import com.example.baozistore.repository.PedidoRepository;

@Service // Classe de Serviço
@Transactional // Cada Método Vai Rodar em Transação (tudo ou nada)
public class ClienteService {

    @Autowired // O Spring Coloca o Repository Automaticamente
    private ClienteRepository repository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public Cliente criar(Cliente cliente) {
        if (cliente.getClienteDesde() == null) {
            cliente.setClienteDesde(LocalDate.now());
        }
        return repository.save(cliente);
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado: " + id));
    }

    public Cliente atualizar(Long id, Cliente dados) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(dados.getNome());
        if (dados.getClienteDesde() != null) {
            cliente.setClienteDesde(dados.getClienteDesde());
        }
        return repository.save(cliente);
    }

    public Cliente excluir(Long id) {
        Cliente cliente = buscarPorId(id);
        if (pedidoRepository.existsByClienteId(id)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Cliente possui pedidos e não pode ser excluído");
        }
        repository.delete(cliente);
        return cliente; // Devolve o Registro Apagado
    }
}
