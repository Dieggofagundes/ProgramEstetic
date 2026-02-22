package com.estetica.api.controller;

import com.estetica.api.entity.Agendamento;
import com.estetica.api.service.AgendaService;
import com.estetica.api.service.CadastroService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agenda")
public class AgendaController {

    private final AgendaService agendaService;
    private final CadastroService cadastroService;

    public AgendaController(AgendaService agendaService, CadastroService cadastroService) {
        this.agendaService = agendaService;
        this.cadastroService = cadastroService;
    }

    @PostMapping
    public Agendamento agendar(@Valid @RequestBody Agendamento agendamento) {
        cadastroService.buscarCliente(agendamento.getCliente().getId());
        cadastroService.buscarTratamento(agendamento.getTratamento().getId());
        return agendaService.salvar(agendamento);
    }

    @GetMapping
    public List<Agendamento> listar() {
        return agendaService.listar();
    }
}
