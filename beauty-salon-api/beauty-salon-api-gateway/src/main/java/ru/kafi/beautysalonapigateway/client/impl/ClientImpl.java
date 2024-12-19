package ru.kafi.beautysalonapigateway.client.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapigateway.client.Client;
import ru.kafi.beautysalonapigateway.config.ClientConfig;

import java.util.List;

@Service
public class ClientImpl extends AbstractClient implements Client {
    protected ClientImpl(ClientConfig clientConfig) {
        super(clientConfig);
    }

    @Override
    public ResponseEntity<?> get(HttpServletRequest request) {
        return super.get(request);
    }

    @Override
    public Page<?> getPage(HttpServletRequest request) {
        return super.getPage(request);
    }

    @Override
    public ResponseEntity<List<?>> getList(HttpServletRequest request) {
        return super.getList(request);
    }

    @Override
    public <T> ResponseEntity<?> create(HttpServletRequest request, T newDto) {
        return super.create(request, newDto);
    }

    @Override
    public <T> ResponseEntity<?> update(HttpServletRequest request, T updateDto) {
        return super.update(request, updateDto);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request) {
        return super.delete(request);
    }
}
