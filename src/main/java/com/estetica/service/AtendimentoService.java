package com.estetica.api.service;

import com.estetica.api.dto.AtendimentoRequest;
import com.estetica.api.dto.ConsumoProdutoRequest;
import com.estetica.api.entity.*;
import com.estetica.api.enums.TipoMovimentoFinanceiro;
import com.estetica.api.exception.BusinessException;
import com.estetica.api.repository.AtendimentoRepository;
import com.estetica.api.repository.MovimentoFinanceiroRepository;
import com.estetica.api.repository.ProdutoEstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final MovimentoFinanceiroRepository movimentoFinanceiroRepository;
    private final ProdutoEstoqueRepository produtoEstoqueRepository;
    private final CadastroService cadastroService;

    public AtendimentoService(AtendimentoRepository atendimentoRepository,
                              MovimentoFinanceiroRepository movimentoFinanceiroRepository,
                              ProdutoEstoqueRepository produtoEstoqueRepository,
                              CadastroService cadastroService) {
        this.atendimentoRepository = atendimentoRepository;
        this.movimentoFinanceiroRepository = movimentoFinanceiroRepository;
        this.produtoEstoqueRepository = produtoEstoqueRepository;
        this.cadastroService = cadastroService;
    }

    @Transactional
    public Atendimento criar(AtendimentoRequest request) {
        Atendimento atendimento = new Atendimento();
        atendimento.setCliente(cadastroService.buscarCliente(request.clienteId()));
        atendimento.setTratamento(cadastroService.buscarTratamento(request.tratamentoId()));
        atendimento.setDataHora(request.dataHora());
        atendimento.setValorCobrado(request.valorCobrado());
        atendimento.setFormaPagamento(request.formaPagamento());
        atendimento.setParcelas(request.parcelas() == null ? 1 : request.parcelas());

        BigDecimal custoTotal = BigDecimal.ZERO;
        if (request.produtosConsumidos() != null) {
            for (ConsumoProdutoRequest item : request.produtosConsumidos()) {
                ProdutoEstoque produto = cadastroService.buscarProduto(item.produtoId());
                if (produto.getQuantidadeAtual().compareTo(item.quantidadeUtilizada()) < 0) {
                    throw new BusinessException("Estoque insuficiente para o produto: " + produto.getNome());
                }

                produto.setQuantidadeAtual(produto.getQuantidadeAtual().subtract(item.quantidadeUtilizada()));
                produtoEstoqueRepository.save(produto);

                ConsumoProduto consumo = new ConsumoProduto();
                consumo.setAtendimento(atendimento);
                consumo.setProduto(produto);
                consumo.setQuantidadeUtilizada(item.quantidadeUtilizada());
                BigDecimal custo = produto.getCustoUnitario().multiply(item.quantidadeUtilizada());
                consumo.setCustoTotal(custo);
                atendimento.getConsumoProdutos().add(consumo);
                custoTotal = custoTotal.add(custo);
            }
        }

        Atendimento salvo = atendimentoRepository.save(atendimento);
        registrarReceita(salvo);
        if (custoTotal.compareTo(BigDecimal.ZERO) > 0) {
            registrarCusto(salvo, custoTotal);
        }
        return salvo;
    }

    public List<Atendimento> listar() {
        return atendimentoRepository.findAll();
    }

    private void registrarReceita(Atendimento atendimento) {
        MovimentoFinanceiro receita = new MovimentoFinanceiro();
        receita.setAtendimento(atendimento);
        receita.setTipo(TipoMovimentoFinanceiro.RECEITA);
        receita.setValor(atendimento.getValorCobrado());
        receita.setDescricao("Receita atendimento #" + atendimento.getId());
        movimentoFinanceiroRepository.save(receita);
    }

    private void registrarCusto(Atendimento atendimento, BigDecimal custoTotal) {
        MovimentoFinanceiro custo = new MovimentoFinanceiro();
        custo.setAtendimento(atendimento);
        custo.setTipo(TipoMovimentoFinanceiro.CUSTO);
        custo.setValor(custoTotal);
        custo.setDescricao("Custo de insumos atendimento #" + atendimento.getId());
        movimentoFinanceiroRepository.save(custo);
    }
}
