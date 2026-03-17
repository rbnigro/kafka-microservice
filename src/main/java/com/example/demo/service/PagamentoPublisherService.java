package com.example.demo.service;

import com.example.demo.DTO.PagamentoDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PagamentoPublisherService {

    private final KafkaTemplate<String, PagamentoDTO> kafkaTemplate;
    private final String TOPIC = "pagamentos-topic";

    public PagamentoPublisherService(KafkaTemplate<String, PagamentoDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void enviar(PagamentoDTO dto) {
        kafkaTemplate.send(TOPIC, dto);
        System.out.println("[PublisherService] Pagamento enviado: " + dto.getNumero());
    }
}
