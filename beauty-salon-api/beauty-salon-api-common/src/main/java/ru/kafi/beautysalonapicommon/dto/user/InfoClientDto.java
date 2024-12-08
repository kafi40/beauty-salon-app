package ru.kafi.beautysalonapicommon.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class InfoClientDto extends InfoUserDto {
    private Long telegramId;
}
