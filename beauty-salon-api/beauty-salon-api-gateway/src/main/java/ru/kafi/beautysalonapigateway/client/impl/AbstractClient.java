package ru.kafi.beautysalonapigateway.client.impl;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kafi.beautysalonapigateway.config.ClientConfig;
import ru.kafi.beautysalonapigateway.util.RestResponsePage;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbstractClient {
    private final RestClient restClient;
    private final String baseUrl;
    private final String authKeyHeaderName;

    protected AbstractClient(ClientConfig clientConfig) {
        this.restClient = clientConfig.restClient();
        this.baseUrl = clientConfig.getBaseUrl();
        this.authKeyHeaderName = clientConfig.getAuthKeyHeaderName();
    }

    protected ResponseEntity<?> get(HttpServletRequest request) {
        URI uri  = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(subPath(request.getRequestURI()))
                .build()
                .toUri();
        try {
            return restClient.get()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .header(authKeyHeaderName, request.getHeader(authKeyHeaderName))
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>(){});
        } catch (Exception e) {
            return error(e);
        }
    }

    protected Page<?> getPage(HttpServletRequest request) {
        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(subPath(request.getRequestURI()))
                .queryParams(setParams(request.getParameterMap()))
                .build()
                .toUri();
        try {
            ParameterizedTypeReference<RestResponsePage<?>> responseType = new ParameterizedTypeReference<>() {};
            ResponseEntity<RestResponsePage<?>> responseEntity = restClient.get()
                    .uri(uri)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(authKeyHeaderName, request.getHeader(authKeyHeaderName))
                    .retrieve()
                    .toEntity(responseType);
            return responseEntity.getBody();
        } catch (Exception e) {
            return new RestResponsePage<>(Collections.emptyList());
        }
    }

    protected ResponseEntity<List<?>> getList(HttpServletRequest request) {
        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(subPath(request.getRequestURI()))
                .queryParams(setParams(request.getParameterMap()))
                .build()
                .toUri();
        try {
            return restClient.get()
                    .uri(uri)
                    .header(authKeyHeaderName, request.getHeader(authKeyHeaderName))
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>(){});
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    protected <T> ResponseEntity<?> create(HttpServletRequest request, T body) {
        URI uri  = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(subPath(request.getRequestURI()))
                .build()
                .toUri();
        try {
            return restClient.post()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .header(authKeyHeaderName, request.getHeader(authKeyHeaderName))
                    .body(body)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
        } catch (Exception e) {
            return error(e);
        }
    }

    protected <T> ResponseEntity<?> update(HttpServletRequest request, T body) {
        URI uri  = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(subPath(request.getRequestURI()))
                .build()
                .toUri();
        try {
            return restClient.patch()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .header(authKeyHeaderName, request.getHeader(authKeyHeaderName))
                    .body(body)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
        } catch (Exception e) {
            return error(e);
        }
    }

    protected ResponseEntity<?> delete(HttpServletRequest request) {
        URI uri  = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(subPath(request.getRequestURI()))
                .build()
                .toUri();
        try {
            return restClient.delete()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .header(authKeyHeaderName, request.getHeader(authKeyHeaderName))
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            return error(e);
        }
    }

    private ResponseEntity<?> error(Exception e) {
        log.error("API gateway (Client): Failed to access the server: {}", e.getMessage());
//        String findStr = "\"status\":";
//        int fIndex = e.getMessage().indexOf(findStr) + findStr.length();
//        int lIndex = fIndex + 3;
//        String status = e.getMessage().substring(fIndex, lIndex);
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(Integer.parseInt("500")));
    }

    private String subPath(String path) {
        return path.substring(4);
    }

    private MultiValueMap<String, String> setParams(Map<String, String[]> values) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        for (String key : values.keySet()) {
            for (String value : values.get(key)) {
                params.add(key, value);
            }
        }
        return params;
    }
}
