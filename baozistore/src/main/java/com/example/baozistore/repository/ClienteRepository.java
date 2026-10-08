package com.example.baozistore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.baozistore.model.Cliente;

// JpaRepository Já Vai Trazer o save, findAll, findById, delete, entre outros comandos
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
