package ru.kafi.beautysalonapigateway.client;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import ru.kafi.beautysalonapicommon.dto.position.NewPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.UpdatePositionDto;

import java.util.List;

public interface PositionClient {
    ResponseEntity<?> get(HttpServletRequest request);

    ResponseEntity<List<?>> getList(HttpServletRequest request);

    ResponseEntity<?> create(HttpServletRequest request, NewPositionDto newPosition);

    ResponseEntity<?> update(HttpServletRequest request, UpdatePositionDto updatePosition);

    ResponseEntity<?> delete(HttpServletRequest request);
}
