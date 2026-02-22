package com.estetica.api.controller;

import com.estetica.api.dto.FinanceiroResumoResponse;
import com.estetica.api.service.FinanceiroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/financeiro")
public class FinanceiroController {

    private final FinanceiroService financeiroService;

    public FinanceiroController(FinanceiroService financeiroService) {
        this.financeiroService = financeiroService;
    }

    @GetMapping("/resumo")
    public FinanceiroResumoResponse resumo() {
        return financeiroService.resumo();
    }
}
