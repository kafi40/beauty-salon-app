package ru.kafi.beautysalonapicommon.dto.appointment;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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
    @Future(message = "The registeredOn should be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registeredOn;
}
