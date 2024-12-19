package ru.kafi.beautysalonbothandler.client.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kafi.beautysalonbothandler.config.ClientConfig;
import ru.kafi.beautysalonbothandler.client.Client;

import java.net.URI;

@Component
public class TgRestClient implements Client {

    private final RestClient client;
    private final String baseUrl;
    private final String authHeader;

    public TgRestClient(ClientConfig config) {
        this.baseUrl = config.getBaseUrl();
        this.authHeader = config.getAuthKeyHeaderName();
        this.client = RestClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Override
    public ResponseEntity<?> get(String path) {

        return null;
    }

    @Override
    public Page<?> getAll(String path) {
        return null;
    }

    @Override
    public ResponseEntity<?> post(String path, Object body) {
        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .build()
                .toUri();
        try {
            return client.post()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .header(authHeader, "test")
                    .body(body)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
        } catch (Exception e) {
            return error(e);
        }
    }

    private ResponseEntity<?> error(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(Integer.parseInt("500")));
    }
}
