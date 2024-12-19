package ru.kafi.beautysalonapicommon.dto.user.employee;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kafi.beautysalonapicommon.dto.user.UpdateUserDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateEmployeeDto extends UpdateUserDto {
    @Positive
    private Long positionId;
}
