package com.estetica.api.dto;

import com.estetica.api.enums.FormaPagamento;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record AtendimentoRequest(
        @NotNull Long clienteId,
        @NotNull Long tratamentoId,
        @NotNull LocalDateTime dataHora,
        @NotNull BigDecimal valorCobrado,
        @NotNull FormaPagamento formaPagamento,
        @Min(1) Integer parcelas,
        @Valid List<ConsumoProdutoRequest> produtosConsumidos
) {
}
