package ru.kafi.beautysalonapigateway.client.impl;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.salon_service.NewSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.UpdateSalonServiceDto;
import ru.kafi.beautysalonapigateway.client.SalonServiceClient;
import ru.kafi.beautysalonapigateway.config.ClientConfig;

import java.util.List;

@Service
public class SalonServiceClientImpl extends Client implements SalonServiceClient {
    protected SalonServiceClientImpl(ClientConfig clientConfig) {
        super(clientConfig);
    }

    @Override
    public ResponseEntity<?> get(HttpServletRequest request) {
        return super.get(request);
    }

    @Override
    public ResponseEntity<List<?>> getList(HttpServletRequest request) {
        return super.getList(request);
    }

    @Override
    public ResponseEntity<?> create(HttpServletRequest request, NewSalonServiceDto newSalonService) {
        return super.post(request, newSalonService);
    }

    @Override
    public ResponseEntity<?> update(HttpServletRequest request, UpdateSalonServiceDto updateSalonService) {
        return super.patch(request, updateSalonService);
    }

    @Override
    public ResponseEntity<?> delete(HttpServletRequest request) {
        return super.delete(request);
    }
}
