package ru.kafi.beautysalonbothandler.client.impl;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kafi.beautysalonapicommon.dto.salon_service.InfoSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;
import ru.kafi.beautysalonapigateway.util.RestResponsePage;
import ru.kafi.beautysalonbothandler.config.ClientConfig;
import ru.kafi.beautysalonbothandler.client.Client;

import java.net.URI;
import java.util.Collections;
import java.util.List;

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
        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .build()
                .toUri();
        try {
            return client.get()
                    .uri(uri)
                    .headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON))
                    .header(authHeader, "test")
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
        } catch (Exception e) {
            return error(e);
        }

    }

    @Override
    public Page<InfoEmployeeDto> getPage(String path) {
        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .build()
                .toUri();
        try {
            ParameterizedTypeReference<RestResponsePage<InfoEmployeeDto>> responseType = new ParameterizedTypeReference<>() {
            };
            ResponseEntity<RestResponsePage<InfoEmployeeDto>> responseEntity = client.get()
                    .uri(uri)
                    .accept(MediaType.APPLICATION_JSON)
                    .header(authHeader, "test")
                    .retrieve()
                    .toEntity(responseType);
            return responseEntity.getBody();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<InfoSalonServiceDto> getAll(String path) {
        URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .path(path)
                .build()
                .toUri();
        try {
            ParameterizedTypeReference<List<InfoSalonServiceDto>> responseType = new ParameterizedTypeReference<>() {
            };
            return client.get()
                    .uri(uri)
                    .header(authHeader, "test")
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .toEntity(responseType)
                    .getBody();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }


    @Override
    public ResponseEntity<?> post(String path, NewClientDto body) {
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
