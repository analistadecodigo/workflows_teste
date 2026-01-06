package com.analistadecodigo.infocep.controllers;

import com.analistadecodigo.infocep.dtos.CepResponseDto;
import com.analistadecodigo.infocep.services.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/infocep")
public class CepController {

    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }

    @GetMapping("/{cep}")
    public CepResponseDto buscarCep(@PathVariable String cep) {
        return cepService.buscarPorCep(cep);
    }
}

