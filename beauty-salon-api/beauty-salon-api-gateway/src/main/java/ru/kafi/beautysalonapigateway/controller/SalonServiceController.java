package ru.kafi.beautysalonapigateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.salon_service.NewSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.UpdateSalonServiceDto;
import ru.kafi.beautysalonapigateway.client.Client;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalonServiceController {
    private final Client salonServiceClient;

    @GetMapping("/api/services/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> publicGet(@Positive @PathVariable final Long serviceId, final HttpServletRequest request) {
        return salonServiceClient.get(request);
    }

    @GetMapping("/api/services")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<?>> publicGetList(final HttpServletRequest request) {
        return salonServiceClient.getList(request);
    }

    @PostMapping("/api/admin/services")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adminCreate(
            @Valid @RequestBody final NewSalonServiceDto newSalonService,
            final HttpServletRequest request) {
        return salonServiceClient.create(request, newSalonService);
    }

    @PatchMapping("/api/admin/services/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> adminUpdate(
            @Positive @PathVariable final Long serviceId,
            @Valid @RequestBody final UpdateSalonServiceDto updateSalonService,
            final HttpServletRequest request) {
        return salonServiceClient.update(request, updateSalonService);
    }

    @DeleteMapping("/api/admin/services/{serviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> adminDelete(
            @Positive @PathVariable final Long serviceId,
            final HttpServletRequest request) {
        return salonServiceClient.delete(request);
    }
}
