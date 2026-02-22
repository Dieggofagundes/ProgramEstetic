package com.estetica.api.repository;

import com.estetica.api.entity.MovimentoFinanceiro;
import com.estetica.api.enums.TipoMovimentoFinanceiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface MovimentoFinanceiroRepository extends JpaRepository<MovimentoFinanceiro, Long> {
    @Query("select coalesce(sum(m.valor),0) from MovimentoFinanceiro m where m.tipo = :tipo")
    BigDecimal somarPorTipo(TipoMovimentoFinanceiro tipo);
}
