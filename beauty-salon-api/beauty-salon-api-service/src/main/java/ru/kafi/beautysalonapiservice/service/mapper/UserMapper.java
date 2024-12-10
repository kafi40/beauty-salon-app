package ru.kafi.beautysalonapiservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.kafi.beautysalonapicommon.dto.user.client.InfoClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.NewEmployeeDto;
import ru.kafi.beautysalonapiservice.service.entity.User;

import java.sql.Date;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "birthday", source = "birthday")
    User toEntity(NewClientDto newUser);

    @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy-MM-dd")
    User toEntity(NewEmployeeDto newEmployee);

    @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy-MM-dd")
    InfoClientDto toClientDto(User user);

    @Mapping(target = "birthday", source = "birthday", dateFormat = "yyyy-MM-dd")
    InfoEmployeeDto toEmployeeDto(User user);

    default Date toDate(String date) {
        if(date!=null) {
            return Date.valueOf(date);
        }
        return null;

    }
}
