package ru.kafi.beautysalonapicommon.dto.user.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonapicommon.enums.Gender;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewEmployeeDto extends NewUserDto {
    @NotBlank(message = "The lastName must be specified")
    @Size(min = 1, max = 32, message = "The number of characters allowed is from 1 to 32")
    private String lastName;
    @NotBlank(message = "The middleName must be specified")
    @Size(min = 1, max = 32, message = "The number of characters allowed is from 1 to 32")
    private String middleName;
    @NotNull(message = "The gender must be specified")
    private Gender gender;
    @NotNull(message = "The birthday must be specified")
    private LocalDate birthday;
    @NotNull
    @Positive
    private Long positionId;
}
