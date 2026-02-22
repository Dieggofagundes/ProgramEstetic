package com.estetica.api.dto;

import java.math.BigDecimal;

public record FinanceiroResumoResponse(
        BigDecimal receita,
        BigDecimal custo,
        BigDecimal lucroReal
) {
}
