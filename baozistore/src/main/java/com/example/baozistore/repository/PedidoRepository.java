package com.example.baozistore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.baozistore.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Consultas Derivadas do Nome do Método
    boolean existsByClienteId(Long clienteId);

    boolean existsByProdutoId(Long produtoId);
}
