package ru.kafi.beautysalonapigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfig {
    @Value("${api-service.url}")
    private String baseUrl;

    @Bean(name = "baseUrl")
    public RestClient restClient() {
        return RestClient
                .builder()
                .baseUrl(baseUrl)
                .build();
    }
}
