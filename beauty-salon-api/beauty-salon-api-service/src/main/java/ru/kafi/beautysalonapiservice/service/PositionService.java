package ru.kafi.beautysalonapiservice.service;

import ru.kafi.beautysalonapicommon.dto.position.InfoPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.NewPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.ShortPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.UpdatePositionDto;

import java.util.List;

public interface PositionService {
    InfoPositionDto get(Long positionId);

    List<ShortPositionDto> getAll();

    InfoPositionDto create(NewPositionDto newPosition);

    InfoPositionDto update(Long positionId, UpdatePositionDto updatePosition);

    void delete(Long positionId);
}
