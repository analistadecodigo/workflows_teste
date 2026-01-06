package com.analistadecodigo.infocep.services;

import com.analistadecodigo.infocep.dtos.CepResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.restclient.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CepService {

    private static final String VIA_CEP_CB = "viaCep";

    private final RestTemplate restTemplate;

    public CepService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @CircuitBreaker(name = VIA_CEP_CB, fallbackMethod = "buscarPorCepFallback")
    public CepResponseDto buscarPorCep(String cep) {
        String url = "https://viacep.com.br/ws/" + cep + "/json/";
        return restTemplate.getForObject(url, CepResponseDto.class);
    }

    /**
     * Fallback chamado quando:
     * - API fora
     * - Timeout
     * - Circuit aberto
     */
    private CepResponseDto buscarPorCepFallback(String cep, Throwable throwable) {
        return CepResponseDto.builder()
                .cep(cep)
                .logradouro("Indisponível")
                .bairro("Indisponível")
                .localidade("Indisponível")
                .uf("NA")
                .build();
    }
}

