package ru.kafi.beautysalonapicommon.dto.appointment;

import lombok.Data;
import ru.kafi.beautysalonapicommon.dto.salon_service.InfoSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.user.client.InfoClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;
import ru.kafi.beautysalonapicommon.enums.Status;

@Data
public class InfoAppointmentDto {
    private Long id;
    private InfoClientDto client;
    private InfoEmployeeDto employee;
    private InfoSalonServiceDto salonService;
    private String registeredOn;
    private Status status;
}
