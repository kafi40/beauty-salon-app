package ru.kafi.beautysalonapiservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.kafi.beautysalonapicommon.dto.user.FullInfoUserDto;
import ru.kafi.beautysalonapicommon.dto.user.InfoClientDto;
import ru.kafi.beautysalonapicommon.dto.user.InfoUserDto;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonapiservice.service.entity.User;

import java.sql.Date;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "birthday", source = "birthday")
    User toEntity(NewUserDto newUserDto);

    @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy-MM-dd")
    FullInfoUserDto toFullDto(User user);

    @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy-MM-dd")
    InfoUserDto toDto(User user);

    @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy-MM-dd")
    InfoClientDto toClientDto(User user);

    default Date toDate(String date) {
        if (date != null) {
            return Date.valueOf(date);
        }
        return null;

    }
}
