package ru.kafi.beautysalonapicommon.dto.user.employee;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kafi.beautysalonapicommon.dto.user.UserDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeDto extends UserDto {
    @Positive
    private Long positionId;
}
