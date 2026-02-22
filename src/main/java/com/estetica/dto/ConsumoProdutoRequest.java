package com.estetica.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ConsumoProdutoRequest(
        @NotNull Long produtoId,
        @NotNull @DecimalMin("0.0001") BigDecimal quantidadeUtilizada
) {
}
