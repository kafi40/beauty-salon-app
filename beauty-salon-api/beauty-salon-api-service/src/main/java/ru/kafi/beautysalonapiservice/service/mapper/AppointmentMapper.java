package ru.kafi.beautysalonapiservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.kafi.beautysalonapicommon.dto.appointment.InfoAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.NewAppointmentDto;
import ru.kafi.beautysalonapiservice.service.entity.Appointment;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {
    Appointment toEntity(NewAppointmentDto newAppointment);

    @Mapping(target = "registeredOn", source = "registeredOn", dateFormat = "yyyy-MM-dd HH:mm:ss")
    InfoAppointmentDto toDto(Appointment appointment);

    default Timestamp toTimestamp(LocalDateTime dateTime) throws DateTimeParseException {
        return Timestamp.valueOf(dateTime);
    }
}
