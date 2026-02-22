package com.estetica.api.controller;

import com.estetica.api.entity.Cliente;
import com.estetica.api.entity.ProdutoEstoque;
import com.estetica.api.entity.Tratamento;
import com.estetica.api.repository.ProdutoEstoqueRepository;
import com.estetica.api.service.CadastroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/cadastro")
public class CadastroController {

    private final CadastroService cadastroService;
    private final ProdutoEstoqueRepository produtoEstoqueRepository;

    public CadastroController(CadastroService cadastroService,
                              ProdutoEstoqueRepository produtoEstoqueRepository) {
        this.cadastroService = cadastroService;
        this.produtoEstoqueRepository = produtoEstoqueRepository;
    }

    @PostMapping("/clientes")
    public Cliente criarCliente(@Valid @RequestBody Cliente cliente) {
        return cadastroService.salvarCliente(cliente);
    }

    @GetMapping("/clientes")
    public List<Cliente> listarClientes() {
        return cadastroService.listarClientes();
    }

    @PostMapping("/tratamentos")
    public Tratamento criarTratamento(@Valid @RequestBody Tratamento tratamento) {
        return cadastroService.salvarTratamento(tratamento);
    }

    @GetMapping("/tratamentos")
    public List<Tratamento> listarTratamentos() {
        return cadastroService.listarTratamentos();
    }

    @PostMapping("/estoque")
    public ProdutoEstoque criarProduto(@Valid @RequestBody ProdutoEstoque produto) {
        return cadastroService.salvarProduto(produto);
    }

    @GetMapping("/estoque")
    public List<ProdutoEstoque> listarProdutos() {
        return cadastroService.listarProdutos();
    }

    @GetMapping("/estoque/baixo")
    public List<ProdutoEstoque> estoqueBaixo() {
        return produtoEstoqueRepository.findAll().stream()
                .filter(ProdutoEstoque::estoqueBaixo)
                .toList();
    }

    @PostMapping("/estoque/{id}/entrada")
    public ProdutoEstoque entradaEstoque(@PathVariable Long id, @RequestParam BigDecimal quantidade) {
        ProdutoEstoque produto = cadastroService.buscarProduto(id);
        produto.setQuantidadeAtual(produto.getQuantidadeAtual().add(quantidade));
        return cadastroService.salvarProduto(produto);
    }
}
