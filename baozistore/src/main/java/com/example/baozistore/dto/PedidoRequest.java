package com.example.baozistore.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

// Corpo do JSON
public record PedidoRequest(
        @NotNull(message = "clienteId é obrigatório") Long clienteId,
        @NotNull(message = "produtoId é obrigatório") Long produtoId,
        @NotNull(message = "quantidade é obrigatória")
        @Positive(message = "quantidade deve ser maior que zero") Integer quantidade) {
}
