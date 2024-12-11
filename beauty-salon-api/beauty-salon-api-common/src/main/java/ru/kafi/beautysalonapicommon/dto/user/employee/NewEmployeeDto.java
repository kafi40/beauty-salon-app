package ru.kafi.beautysalonapicommon.dto.user.employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonapicommon.enums.Gender;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewEmployeeDto extends NewUserDto {
    @NotBlank
    @Size(min = 1, max = 32)
    private String lastName;
    @NotBlank
    @Size(min = 1, max = 32)
    private String middleName;
    @NotBlank
    private Gender gender;
    @NotBlank
    private String birthday;
    @NotNull
    @Positive
    private Long positionId;
}
