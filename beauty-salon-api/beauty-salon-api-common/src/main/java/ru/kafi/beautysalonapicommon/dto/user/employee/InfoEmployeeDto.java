package ru.kafi.beautysalonapicommon.dto.user.employee;

import lombok.Data;
import lombok.EqualsAndHashCode;

import ru.kafi.beautysalonapicommon.dto.position.InfoPositionDto;
import ru.kafi.beautysalonapicommon.dto.user.InfoUserDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class InfoEmployeeDto extends InfoUserDto {
    private InfoPositionDto position;
}
