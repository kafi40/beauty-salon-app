package ru.kafi.beautysalonapigateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.position.NewPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.UpdatePositionDto;
import ru.kafi.beautysalonapigateway.client.PositionClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PositionController {
    private final PositionClient positionClient;

    @GetMapping("/api/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> get(@Positive @PathVariable Long positionId, HttpServletRequest request) {
        log.info("API gateway (PositionController): Get position with ID={}", positionId);
        return positionClient.get(request);
    }

    @GetMapping("/api/admin/positions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<?>> getAll(HttpServletRequest request) {
        log.info("API gateway (PositionController): Get positions");
        return positionClient.getList(request);
    }

    @PostMapping("/api/admin/positions")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody NewPositionDto newPosition, HttpServletRequest request) {
        log.info("API gateway (PositionController): Create position={}", newPosition);
        return positionClient.create(request, newPosition);
    }

    @PatchMapping("/api/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(
            @Positive @PathVariable Long positionId,
            @Valid @RequestBody UpdatePositionDto updatePosition,
            HttpServletRequest request) {
        log.info("API gateway (PositionController): Update position={} with ID={}", updatePosition, positionId);
        return positionClient.update(request, updatePosition);
    }

    @DeleteMapping("/api/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@Positive @PathVariable Long positionId, HttpServletRequest request) {
        log.info("API gateway (PositionController): Delete position with ID={}", positionId);
        return positionClient.delete(request);
    }
}
