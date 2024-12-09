package ru.kafi.beautysalonapigateway.client.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.kafi.beautysalonapicommon.dto.position.NewPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.UpdatePositionDto;
import ru.kafi.beautysalonapigateway.client.PositionClient;

import java.util.List;

@Service
public class PositionClientImpl extends Client implements PositionClient {
    protected PositionClientImpl(RestClient restClient) {
        super(restClient);
    }

    @Override
    public ResponseEntity<?> get(HttpServletRequest request) {
        return super.get(request.getRequestURI());
    }

    @Override
    public ResponseEntity<List<?>> getList(HttpServletRequest request) {
        return super.getList(request.getRequestURI(), request);
    }

    @Override
    public ResponseEntity<?> create(HttpServletRequest request, NewPositionDto newPosition) {
        return super.post(request.getRequestURI(), newPosition);
    }

    @Override
    public ResponseEntity<?> update(HttpServletRequest request, UpdatePositionDto updatePosition) {
        return super.patch(request.getRequestURI(), updatePosition);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request) {
        return super.delete(request.getRequestURI());
    }
}
