package ru.kafi.beautysalonapiservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.appointment.InfoAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.NewAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.UpdateAppointmentDto;
import ru.kafi.beautysalonapicommon.util.Util;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;
import ru.kafi.beautysalonapiservice.repository.AppointmentRepository;
import ru.kafi.beautysalonapiservice.repository.SalonServiceRepository;
import ru.kafi.beautysalonapiservice.service.AppointmentService;
import ru.kafi.beautysalonapiservice.service.entity.Appointment;
import ru.kafi.beautysalonapiservice.service.entity.SalonService;
import ru.kafi.beautysalonapiservice.service.mapper.AppointmentMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public InfoAppointmentDto get(Long appointmentId) {
        Appointment appointment = getAppointment(appointmentId);
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public List<InfoAppointmentDto> getAll() {
        return List.of();
    }

    @Override
    public InfoAppointmentDto create(NewAppointmentDto newAppointment) {
        SalonService salonService = salonServiceRepository.findById(newAppointment.getSalonServiceId())
                .orElseThrow(() -> new NotFoundException(
                        "API service (SalonServiceService): Service with ID=" + newAppointment.getSalonServiceId() + " not found"));
        LocalDateTime start = Util.toLocalDateTime(newAppointment.getRegisteredOn());
        LocalDateTime end = Util.toLocalDateTime(newAppointment.getRegisteredOn()).plusMinutes(salonService.getDuration());
        List<Appointment> appointments = appointmentRepository.findFreeSlot(newAppointment.getEmployeeId(), start, end);
        if (!appointments.isEmpty())
            throw new RuntimeException("Время занято");
        Appointment appointment = appointmentMapper.toEntity(newAppointment);
        return null;
    }

    @Override
    public InfoAppointmentDto update(Long appointmentId, UpdateAppointmentDto updateAppointment) {
        return null;
    }

    @Override
    public void delete(Long appointmentId) {

    }

    private Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (AppointmentService): Appointment with ID=" + appointmentId + " not found"));
    }
}
