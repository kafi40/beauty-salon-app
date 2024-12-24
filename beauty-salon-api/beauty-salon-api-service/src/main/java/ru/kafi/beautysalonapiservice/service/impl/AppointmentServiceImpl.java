package ru.kafi.beautysalonapiservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.appointment.InfoAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.NewAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.UpdateAppointmentDto;
import ru.kafi.beautysalonapicommon.enums.Status;
import ru.kafi.beautysalonapicommon.util.Util;
import ru.kafi.beautysalonapiservice.exception.MakeAppointmentException;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;
import ru.kafi.beautysalonapiservice.repository.AppointmentRepository;
import ru.kafi.beautysalonapiservice.repository.SalonServiceRepository;
import ru.kafi.beautysalonapiservice.repository.UserRepository;
import ru.kafi.beautysalonapiservice.service.AppointmentService;
import ru.kafi.beautysalonapiservice.service.entity.Appointment;
import ru.kafi.beautysalonapiservice.service.entity.SalonService;
import ru.kafi.beautysalonapiservice.service.entity.User;
import ru.kafi.beautysalonapiservice.service.mapper.AppointmentMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final SalonServiceRepository salonServiceRepository;
    private final UserRepository userRepository;
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
        SalonService salonService = getSalonService(newAppointment.getSalonServiceId());
        LocalDateTime start = newAppointment.getRegisteredOn();
        LocalDateTime end = newAppointment.getRegisteredOn().plusMinutes(salonService.getDuration());
        List<Appointment> appointments = appointmentRepository.findFreeSlot(newAppointment.getEmployeeId(), start, end);
        if (!appointments.isEmpty()) {
            throw new MakeAppointmentException("API service (AppointmentService): This time has already been booked");
        }
        User employee = getUser(newAppointment.getEmployeeId());
        Appointment appointment = appointmentMapper.toEntity(newAppointment);
        User client = getUser(newAppointment.getClientId());
        appointment.setClient(client);
        appointment.setEmployee(employee);
        appointment.setSalonService(salonService);
        appointment.setStatus(Status.BOOKED);
        appointment = appointmentRepository.save(appointment);
        return appointmentMapper.toDto(appointment);
    }

    @Override
    public InfoAppointmentDto update(Long appointmentId, UpdateAppointmentDto updateAppointment) {
        return null;
    }

    @Override
    public void delete(Long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    private Appointment getAppointment(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (AppointmentService): Appointment with ID=" + appointmentId + " not found"));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (AppointmentService): User with ID=" + userId + " not found"));
    }

    private SalonService getSalonService(Long salonServiceId) {
        return salonServiceRepository.findById(salonServiceId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (SalonServiceService): Service with ID=" + salonServiceId + " not found"));
    }
}
