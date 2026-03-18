package com.example.demo.strategy;

import com.example.demo.dto.PagamentoDTO;

public interface PagamentoStrategy {
    void processarEEnviar(PagamentoDTO dto);
    boolean seAplica(PagamentoDTO dto);
}
