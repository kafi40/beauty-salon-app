package ru.kafi.beautysalonbothandler.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class ClientConfig {
    @Value("${api-service.url}")
    private String baseUrl;
    @Value("${security.auth.header-name}")
    private String authKeyHeaderName;
}
