package com.estetica.api.service;

import com.estetica.api.entity.Cliente;
import com.estetica.api.entity.ProdutoEstoque;
import com.estetica.api.entity.Tratamento;
import com.estetica.api.exception.NotFoundException;
import com.estetica.api.repository.ClienteRepository;
import com.estetica.api.repository.ProdutoEstoqueRepository;
import com.estetica.api.repository.TratamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroService {

    private final ClienteRepository clienteRepository;
    private final TratamentoRepository tratamentoRepository;
    private final ProdutoEstoqueRepository produtoEstoqueRepository;

    public CadastroService(ClienteRepository clienteRepository,
                           TratamentoRepository tratamentoRepository,
                           ProdutoEstoqueRepository produtoEstoqueRepository) {
        this.clienteRepository = clienteRepository;
        this.tratamentoRepository = tratamentoRepository;
        this.produtoEstoqueRepository = produtoEstoqueRepository;
    }

    public Cliente salvarCliente(Cliente cliente) { return clienteRepository.save(cliente); }
    public List<Cliente> listarClientes() { return clienteRepository.findAll(); }

    public Tratamento salvarTratamento(Tratamento tratamento) { return tratamentoRepository.save(tratamento); }
    public List<Tratamento> listarTratamentos() { return tratamentoRepository.findAll(); }

    public ProdutoEstoque salvarProduto(ProdutoEstoque produto) { return produtoEstoqueRepository.save(produto); }
    public List<ProdutoEstoque> listarProdutos() { return produtoEstoqueRepository.findAll(); }

    public Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    public Tratamento buscarTratamento(Long id) {
        return tratamentoRepository.findById(id).orElseThrow(() -> new NotFoundException("Tratamento não encontrado"));
    }

    public ProdutoEstoque buscarProduto(Long id) {
        return produtoEstoqueRepository.findById(id).orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }
}
