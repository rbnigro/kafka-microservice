package com.example.demo.strategy;

import com.example.demo.DTO.PagamentoDTO;

public interface PagamentoStrategy {
    void processarEEnviar(PagamentoDTO dto);
    boolean seAplica(PagamentoDTO dto);
}
