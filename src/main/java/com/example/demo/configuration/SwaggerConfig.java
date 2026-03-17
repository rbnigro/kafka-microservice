package com.example.demo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Pagamentos com Kafka")
                        .version("1.0")
                        .description("Microserviço para processamento de pagamentos utilizando Virtual Threads e Kafka.")
                        .contact(new Contact()
                                .name("Ronney")
                                .email("ronneynigro@gmail.com")));
    }

}
