package ru.kafi.beautysalonapigateway.client.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.kafi.beautysalonapicommon.dto.salon_service.NewSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.UpdateSalonServiceDto;
import ru.kafi.beautysalonapigateway.client.SalonServiceClient;

import java.util.List;

@Service
public class SalonServiceClientImpl extends Client implements SalonServiceClient {
    protected SalonServiceClientImpl(RestClient restClient) {
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
    public ResponseEntity<?> create(HttpServletRequest request, NewSalonServiceDto newSalonService) {
        return super.post(request.getRequestURI(), newSalonService);
    }

    @Override
    public ResponseEntity<?> update(HttpServletRequest request, UpdateSalonServiceDto updateSalonService) {
        return super.patch(request.getRequestURI(), updateSalonService);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request) {
        return super.delete(request.getRequestURI());
    }
}
