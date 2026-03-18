package com.example.demo.controller;

import com.example.demo.dto.PagamentoDTO;
import com.example.demo.service.PagamentoPublisherService;
import com.example.demo.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pagamentos")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Pagamentos", description = "Endpoints para envio e processamento de pagamentos")
public class PagamentoController {

    // http://localhost:8080/swagger-ui.html

    private final KafkaTemplate<String, PagamentoDTO> kafkaTemplate;

    private final PagamentoService pagamentoService;
    private final PagamentoPublisherService publisherService;

    private final String TOPIC = "pagamentos-topic";

    public PagamentoController(KafkaTemplate<String,
                               PagamentoDTO> kafkaTemplate,
                               PagamentoService pagamentoService,
                               PagamentoPublisherService publisherService) {
        this.kafkaTemplate = kafkaTemplate;
        this.pagamentoService = pagamentoService;
        this.publisherService = publisherService;
    }

    @Operation(summary = "Envia uma notificação de pagamento simples")
    @PostMapping("/v1/notificar")
    public ResponseEntity<String> enviarSimples(@RequestBody PagamentoDTO dto) {
        publisherService.enviar(dto);
        return ResponseEntity.ok("Pagamento nº " + dto.getNumero() + " enviado para fila.");
    }

    @Operation(summary = "Processa o pagamento via Strategy e envia ao Kafka")
    @PostMapping("/v1/processar")
    public ResponseEntity<PagamentoDTO> processarEDevolver(@RequestBody PagamentoDTO dto) {
        PagamentoDTO processado = pagamentoService.processar(dto);
        return ResponseEntity.ok(processado);
    }

    @PostMapping("/v1/enviar")
    public ResponseEntity<String> processarEEnviar(@RequestBody PagamentoDTO pagamentoDTO) {
        publisherService.enviar(pagamentoDTO);

        return ResponseEntity.ok("Pagamento para processamento.");
    }
}
