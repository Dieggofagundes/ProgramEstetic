package com.estetica.api.repository;

import com.estetica.api.entity.ProdutoEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoEstoqueRepository extends JpaRepository<ProdutoEstoque, Long> {
    List<ProdutoEstoque> findByQuantidadeAtualLessThanEqual(BigDecimal quantidade);
}
