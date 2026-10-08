package com.example.baozistore.model;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity // Vai Virar a Tabela "cliente"
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id Auto-Incremento
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome; // Entra o Nome + RU

    private LocalDate clienteDesde; // Se Não Informada, a API Usará a Data da Requisição
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getClienteDesde() {
        return clienteDesde;
    }

    public void setClienteDesde(LocalDate clienteDesde) {
        this.clienteDesde = clienteDesde;
    }
}
