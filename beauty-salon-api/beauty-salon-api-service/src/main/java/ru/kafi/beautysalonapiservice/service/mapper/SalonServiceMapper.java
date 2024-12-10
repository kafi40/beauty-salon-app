package ru.kafi.beautysalonapiservice.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.kafi.beautysalonapicommon.dto.salon_service.InfoSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.NewSalonServiceDto;
import ru.kafi.beautysalonapiservice.service.entity.SalonService;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SalonServiceMapper {
    SalonService toEntity(NewSalonServiceDto newSalonService);

    InfoSalonServiceDto toDto(SalonService salonService);
}
