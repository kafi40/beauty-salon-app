package ru.kafi.beautysalonapigateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.appointment.NewAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.UpdateAppointmentDto;
import ru.kafi.beautysalonapigateway.client.Client;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final Client appointmentClient;

    @GetMapping("/api/admin/appointments/{appointmentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> adminGet(@Positive @PathVariable final Long appointmentId, final HttpServletRequest request) {
        return appointmentClient.get(request);
    }

    @GetMapping("/api/admin/appointments")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<?>> adminGetList(final HttpServletRequest request) {
        return appointmentClient.getList(request);
    }

    @PostMapping("/api/appointments")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> privateCreate(
            @Valid @RequestBody final NewAppointmentDto newAppointment,
            final HttpServletRequest request) {
        return appointmentClient.create(request, newAppointment);
    }

    @PatchMapping("/api/appointments/{appointmentId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> privateUpdate(
            @Positive @PathVariable final Long appointmentId,
            @Valid @RequestBody final UpdateAppointmentDto updateAppointment,
            HttpServletRequest request) {
        return appointmentClient.update(request, updateAppointment);
    }

    @DeleteMapping("/api/admin/appointments/{appointmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> adminDelete(@Positive @PathVariable final Long appointmentId, final HttpServletRequest request) {
        return appointmentClient.delete(request);
    }
}
