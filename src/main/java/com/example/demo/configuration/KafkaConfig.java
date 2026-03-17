package com.example.demo.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic pagamentosTopic() {
        return TopicBuilder.name("pagamentos-topic")
                .partitions(3) // Recomendado para paralelismo
                .replicas(1)   // Ajuste conforme o número de brokers que você tem
                .build();
    }

}
