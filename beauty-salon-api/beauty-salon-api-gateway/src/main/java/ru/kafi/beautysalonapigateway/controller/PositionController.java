package ru.kafi.beautysalonapigateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.position.NewPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.UpdatePositionDto;
import ru.kafi.beautysalonapigateway.client.Client;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PositionController {
    private final Client positionClient;

    @GetMapping("/api/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> get(@Positive @PathVariable final Long positionId, final HttpServletRequest request) {
        return positionClient.get(request);
    }

    @GetMapping("/api/admin/positions")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<?>> getAll(final HttpServletRequest request) {
        return positionClient.getList(request);
    }

    @PostMapping("/api/admin/positions")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(
            @Valid @RequestBody final NewPositionDto newPosition,
            final HttpServletRequest request) {
        return positionClient.create(request, newPosition);
    }

    @PatchMapping("/api/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> update(
            @Positive @PathVariable final Long positionId,
            @Valid @RequestBody final UpdatePositionDto updatePosition,
            HttpServletRequest request) {
        return positionClient.update(request, updatePosition);
    }

    @DeleteMapping("/api/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@Positive @PathVariable final Long positionId, final HttpServletRequest request) {
        return positionClient.delete(request);
    }
}
