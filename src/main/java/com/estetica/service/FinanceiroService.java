package com.estetica.api.service;

import com.estetica.api.dto.FinanceiroResumoResponse;
import com.estetica.api.enums.TipoMovimentoFinanceiro;
import com.estetica.api.repository.MovimentoFinanceiroRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FinanceiroService {

    private final MovimentoFinanceiroRepository movimentoFinanceiroRepository;

    public FinanceiroService(MovimentoFinanceiroRepository movimentoFinanceiroRepository) {
        this.movimentoFinanceiroRepository = movimentoFinanceiroRepository;
    }

    public FinanceiroResumoResponse resumo() {
        BigDecimal receita = movimentoFinanceiroRepository.somarPorTipo(TipoMovimentoFinanceiro.RECEITA);
        BigDecimal custo = movimentoFinanceiroRepository.somarPorTipo(TipoMovimentoFinanceiro.CUSTO);
        return new FinanceiroResumoResponse(receita, custo, receita.subtract(custo));
    }
}
