package ru.kafi.beautysalonapiservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.appointment.InfoAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.NewAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.UpdateAppointmentDto;
import ru.kafi.beautysalonapiservice.service.AppointmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/appointments/{appointmentId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoAppointmentDto privateGet(@PathVariable final Long appointmentId) {
        return appointmentService.get(appointmentId);
    }

    @GetMapping("/admin/appointments/")
    @ResponseStatus(HttpStatus.OK)
    public List<InfoAppointmentDto> adminGetList() {
        return appointmentService.getAll();
    }

    @PostMapping("/appointments")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoAppointmentDto privateCreate(@RequestBody final NewAppointmentDto newAppointment) {
        return appointmentService.create(newAppointment);
    }

    @PatchMapping("/appointments/{appointmentId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoAppointmentDto privateUpdate(
            @PathVariable final Long appointmentId,
            @RequestBody final UpdateAppointmentDto updateAppointment) {
        return appointmentService.update(appointmentId, updateAppointment);
    }

    @DeleteMapping("/appointments/{appointmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void privateDelete(@PathVariable final Long appointmentId) {
        appointmentService.delete(appointmentId);
    }
}
