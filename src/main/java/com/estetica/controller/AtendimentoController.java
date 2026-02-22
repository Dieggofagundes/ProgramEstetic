package com.estetica.api.controller;

import com.estetica.api.dto.AtendimentoRequest;
import com.estetica.api.entity.Atendimento;
import com.estetica.api.service.AtendimentoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @PostMapping
    public Atendimento criar(@Valid @RequestBody AtendimentoRequest request) {
        return atendimentoService.criar(request);
    }

    @GetMapping
    public List<Atendimento> listar() {
        return atendimentoService.listar();
    }
}
