package ru.kafi.beautysalonapicommon.dto.user.client;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewClientDto extends NewUserDto {
}
