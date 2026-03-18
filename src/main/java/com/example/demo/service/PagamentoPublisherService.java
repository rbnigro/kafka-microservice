package com.example.demo.service;

import com.example.demo.dto.PagamentoDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PagamentoPublisherService {

    private final KafkaTemplate<String, PagamentoDTO> kafkaTemplate;
    private final String TOPIC = "pagamento.request.topic.v2";

    public PagamentoPublisherService(KafkaTemplate<String, PagamentoDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviar(PagamentoDTO dto) {
        kafkaTemplate.send(TOPIC, dto);
        System.out.println("[PublisherService] Pagamento enviado: " + dto.getNumero());
    }
}
