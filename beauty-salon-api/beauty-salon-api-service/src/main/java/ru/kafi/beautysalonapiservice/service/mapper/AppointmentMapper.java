package ru.kafi.beautysalonapiservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.kafi.beautysalonapicommon.dto.appointment.InfoAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.NewAppointmentDto;
import ru.kafi.beautysalonapiservice.service.entity.Appointment;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {
    Appointment toEntity(NewAppointmentDto newAppointment);

    InfoAppointmentDto toDto(Appointment appointment);
}
