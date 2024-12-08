package ru.kafi.beautysalonapicommon.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FullInfoUserDto extends InfoUserDto {
    private Long telegramId;
    private InfoPositionDto position;
}
