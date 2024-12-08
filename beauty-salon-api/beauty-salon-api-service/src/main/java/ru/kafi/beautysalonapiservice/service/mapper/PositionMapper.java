package ru.kafi.beautysalonapiservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.kafi.beautysalonapicommon.dto.user.InfoPositionDto;
import ru.kafi.beautysalonapiservice.service.entity.Position;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PositionMapper {
    Position toEntity(Position position);

    Position toDto(InfoPositionDto position);
}
