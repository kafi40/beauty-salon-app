package ru.kafi.beautysalonapiservice.service;

import ru.kafi.beautysalonapicommon.dto.appointment.InfoAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.NewAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.UpdateAppointmentDto;

import java.util.List;

public interface AppointmentService {
    InfoAppointmentDto get(Long appointmentId);

    List<InfoAppointmentDto> getAll();

    InfoAppointmentDto create(NewAppointmentDto newAppointment);

    InfoAppointmentDto update(Long appointmentId, UpdateAppointmentDto updateAppointment);

    void delete(Long appointmentId);
}
