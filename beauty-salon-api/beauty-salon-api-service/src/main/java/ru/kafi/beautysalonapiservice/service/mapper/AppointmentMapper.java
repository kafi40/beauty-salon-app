package ru.kafi.beautysalonapiservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.kafi.beautysalonapicommon.dto.appointment.InfoAppointmentDto;
import ru.kafi.beautysalonapicommon.dto.appointment.NewAppointmentDto;
import ru.kafi.beautysalonapiservice.service.entity.Appointment;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {
    Appointment toEntity(NewAppointmentDto newAppointment);

    @Mapping(target = "registeredOn", source = "registeredOn", dateFormat = "yyyy-MM-dd HH:mm:ss")
    InfoAppointmentDto toDto(Appointment appointment);

    default Timestamp toTimestamp(String dateTime) throws DateTimeParseException {
        return Timestamp.valueOf(
                LocalDateTime.parse(
                        URLDecoder.decode(dateTime, StandardCharsets.UTF_8),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                )
        );
    }
}
