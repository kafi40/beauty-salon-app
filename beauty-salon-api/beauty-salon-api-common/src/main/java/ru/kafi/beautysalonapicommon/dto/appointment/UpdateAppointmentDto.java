package ru.kafi.beautysalonapicommon.dto.appointment;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import ru.kafi.beautysalonapicommon.enums.Status;

@Data
public class UpdateAppointmentDto {
    @Positive(message = "The employee ID must be greater than 0")
    private Long employeeId;
    @Positive(message = "The service ID must be greater than 0")
    private Long salonServiceId;
    private String registeredOn;
    private Status status;
}
