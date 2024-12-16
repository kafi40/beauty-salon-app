package ru.kafi.beautysalonapigateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.salon_service.NewSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.UpdateSalonServiceDto;
import ru.kafi.beautysalonapigateway.client.SalonServiceClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SalonServiceController {
    private final SalonServiceClient salonServiceClient;

    @GetMapping("/api/services/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getPublic(@Positive @PathVariable final Long serviceId, final HttpServletRequest request) {
        log.info("API service (SalonServiceController): Get public service with ID={}", serviceId);
        return salonServiceClient.get(request);
    }

    @GetMapping("/api/services")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<?>> getListPublic(final HttpServletRequest request) {
        log.info("API service (SalonServiceController): Public get services");
        return salonServiceClient.getList(request);
    }

    @PostMapping("/api/admin/services")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createAdmin(
            @Valid @RequestBody final NewSalonServiceDto newSalonService,
            final HttpServletRequest request) {
        log.info("API service (SalonServiceController): Admin create new service={}", newSalonService);
        return salonServiceClient.create(request, newSalonService);
    }

    @PatchMapping("/api/admin/services/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateAdmin(
            @Positive @PathVariable final Long serviceId,
            @Valid @RequestBody final UpdateSalonServiceDto updateSalonService,
            final HttpServletRequest request) {
        log.info("API service (SalonServiceController): Admin update service={} with ID={}", updateSalonService, serviceId);
        return salonServiceClient.update(request, updateSalonService);
    }

    @DeleteMapping("/api/admin/services/{serviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteAdmin(
            @Positive @PathVariable final Long serviceId,
            final HttpServletRequest request) {
        log.info("API service (SalonServiceController): Admin delete service with ID={}", serviceId);
        return salonServiceClient.delete(request);
    }
}
