package com.estetica.api.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "consumo_produtos")
public class ConsumoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "atendimento_id")
    private Atendimento atendimento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id")
    private ProdutoEstoque produto;

    @Column(nullable = false)
    private BigDecimal quantidadeUtilizada;

    @Column(nullable = false)
    private BigDecimal custoTotal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Atendimento getAtendimento() { return atendimento; }
    public void setAtendimento(Atendimento atendimento) { this.atendimento = atendimento; }
    public ProdutoEstoque getProduto() { return produto; }
    public void setProduto(ProdutoEstoque produto) { this.produto = produto; }
    public BigDecimal getQuantidadeUtilizada() { return quantidadeUtilizada; }
    public void setQuantidadeUtilizada(BigDecimal quantidadeUtilizada) { this.quantidadeUtilizada = quantidadeUtilizada; }
    public BigDecimal getCustoTotal() { return custoTotal; }
    public void setCustoTotal(BigDecimal custoTotal) { this.custoTotal = custoTotal; }
}
