package ru.kafi.beautysalonapigateway.client;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import ru.kafi.beautysalonapicommon.dto.salon_service.NewSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.UpdateSalonServiceDto;

import java.util.List;

public interface SalonServiceClient {
    ResponseEntity<?> get(HttpServletRequest request);

    ResponseEntity<List<?>> getList(HttpServletRequest request);

    ResponseEntity<?> create(HttpServletRequest request, NewSalonServiceDto newSalonService);

    ResponseEntity<?> update(HttpServletRequest request, UpdateSalonServiceDto updateSalonService);

    ResponseEntity<?> delete(HttpServletRequest request);
}
