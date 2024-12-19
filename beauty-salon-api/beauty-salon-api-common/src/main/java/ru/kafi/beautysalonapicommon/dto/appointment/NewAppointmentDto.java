package ru.kafi.beautysalonapicommon.dto.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import ru.kafi.beautysalonapicommon.enums.Status;

@Data
public class NewAppointmentDto {
    @NotNull(message = "The client ID cannot be null")
    @Positive(message = "The client ID must be greater than 0")
    private Long clientId;
    @NotNull(message = "The employee ID cannot be null")
    @Positive(message = "The employee ID must be greater than 0")
    private Long employeeId;
    @NotNull(message = "The service ID cannot be null")
    @Positive(message = "The service ID must be greater than 0")
    private Long salonServiceId;
    @NotBlank(message = "The date and time must be specified")
    private String registeredOn;
    @NotNull(message = "The status must be specified")
    private Status status;
}
