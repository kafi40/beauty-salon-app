package ru.kafi.beautysalonapicommon.dto.user.client;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kafi.beautysalonapicommon.dto.user.InfoUserDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class InfoClientDto extends InfoUserDto {
    private Long telegramId;
}
