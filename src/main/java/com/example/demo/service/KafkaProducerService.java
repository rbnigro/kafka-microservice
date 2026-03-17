package com.example.demo.service;

import com.example.demo.DTO.PagamentoDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private final KafkaTemplate<String, PagamentoDTO> kafkaTemplate;
    private final String TOPIC = "pagamentos-topic";

    public KafkaProducerService(KafkaTemplate<String, PagamentoDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviarPagamento(PagamentoDTO dto) {
        kafkaTemplate.send(TOPIC, dto);
    }
}
