package ru.kafi.beautysalonapigateway.client.impl;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Slf4j
public abstract class Client {
    protected final RestClient restClient;
    @Value("${api-service.url}")
    protected String baseUrl;

    protected Client(RestClient restClient) {
        this.restClient = restClient;
    }

    protected ResponseEntity<?> get(String path) {
        log.info("API gateway (Client): Try get by path={}", path);
        URI uri  = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .build()
                .toUri();
        try {
            return restClient.get()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>(){});
        } catch (Exception e) {
            return error(e);
        }
    }

    protected List<ResponseEntity<?>> getAll(String path, HttpServletRequest request) {
        log.info("API gateway (Client): Try getAll by path={}", path);
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        for (String key : request.getParameterMap().keySet()) {
            for (String value : request.getParameterMap().get(key)) {
                params.add(key, value);
            }
        }
        URI uri  = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .queryParams(params)
                .build()
                .toUri();
        try {
            return restClient.get()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .retrieve()
                    .body(new ParameterizedTypeReference<>(){});
        } catch (Exception e) {
            return Collections.singletonList(error(e));
        }
    }

    protected ResponseEntity<?> post(String path, Object body) {
        log.info("API gateway (Client): Try post by path={}", path);
        URI uri  = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .build()
                .toUri();

        try {
            return restClient.post()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .body(body)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
        } catch (Exception e) {
            return error(e);
        }
    }

    protected ResponseEntity<?> patch(String path, Object body) {
        log.info("API gateway (Client): Try patch by path={}", path);
        URI uri  = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .build()
                .toUri();
        try {
            return restClient.patch()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .body(body)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
        } catch (Exception e) {
            return error(e);
        }
    }

    protected ResponseEntity<?> delete(String path) {
        log.info("API gateway (Client): Try to delete by path={}", path);
        URI uri  = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .build()
                .toUri();
        try {
            return restClient.delete()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return error(e);
        }
    }

    private ResponseEntity<?> error(Exception e) {
        log.error("API gateway (Client): Failed to access the server: {}", e.getMessage());
        String status = e.getMessage().substring(0, 3);
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(Integer.parseInt(status)));
    }
}
