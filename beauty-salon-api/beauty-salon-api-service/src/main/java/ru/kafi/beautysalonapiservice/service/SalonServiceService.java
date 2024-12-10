package ru.kafi.beautysalonapiservice.service;

import ru.kafi.beautysalonapicommon.dto.salon_service.InfoSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.NewSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.UpdateSalonServiceDto;

import java.util.List;

public interface SalonServiceService {
    InfoSalonServiceDto get(Long serviceId);

    List<InfoSalonServiceDto> getAll();

    InfoSalonServiceDto create(NewSalonServiceDto newSalonService);

    InfoSalonServiceDto update(Long serviceId, UpdateSalonServiceDto updateSalonService);

    void delete(Long serviceId);
}
