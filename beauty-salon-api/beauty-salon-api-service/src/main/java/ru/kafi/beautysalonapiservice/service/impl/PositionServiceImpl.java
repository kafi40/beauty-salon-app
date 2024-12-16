package ru.kafi.beautysalonapiservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.position.InfoPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.NewPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.ShortPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.UpdatePositionDto;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;
import ru.kafi.beautysalonapiservice.exception.ValueAlreadyUsedException;
import ru.kafi.beautysalonapiservice.repository.PositionRepository;
import ru.kafi.beautysalonapiservice.service.PositionService;
import ru.kafi.beautysalonapiservice.service.entity.Position;
import ru.kafi.beautysalonapiservice.service.mapper.PositionMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PositionServiceImpl implements PositionService {
    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    @Override
    public InfoPositionDto get(Long positionId) {
        log.info("API service (PositionService): Try get()");
        Position position =  getPosition(positionId);
        log.info("API service (PositionService): Finish get()");
        return positionMapper.toDto(position);
    }

    @Override
    public List<ShortPositionDto> getAll() {
        log.info("API service (PositionService): Try getAll()");
        List<Position> positions = positionRepository.findAll();
        log.info("API service (PositionService): Finish getAll()");
        return positions.stream()
                .map(positionMapper::toShortDto)
                .toList();
    }

    @Override
    @Transactional
    public InfoPositionDto create(NewPositionDto newPosition) {
        log.info("API service (PositionService): Try create()");
        Position position = positionMapper.toEntity(newPosition);
        if (positionRepository.existsByNameWithinIgnoreCase(position.getName()))
            throw new ValueAlreadyUsedException("API service (PositionService): The name is already in use");
        position = positionRepository.save(position);
        log.info("API service (PositionService): Finish create()");
        return positionMapper.toDto(position);
    }

    @Override
    @Transactional
    public InfoPositionDto update(Long positionId, UpdatePositionDto updatePosition) {
        log.info("API service (PositionService): Try update()");
        Position position = getPosition(positionId);
        if (updatePosition.getName() != null) {
            if (positionRepository.existsByNameWithinIgnoreCase(position.getName()))
                throw new ValueAlreadyUsedException("API service (PositionService): The name is already in use");
            position.setName(updatePosition.getName());
        }
        if (updatePosition.getMinSalary() != null)
            position.setMinSalary(updatePosition.getMinSalary());
        if (updatePosition.getMaxSalary() != null)
            position.setMaxSalary(updatePosition.getMaxSalary());
        position = positionRepository.save(position);
        log.info("API service (PositionService): Finish update()");
        return positionMapper.toDto(position);
    }

    @Override
    @Transactional
    public void delete(Long positionId) {
        log.info("API service (PositionService): Try to delete()");
        positionRepository.deleteById(positionId);
        log.info("API service (PositionService): Finish delete()");
    }

    private Position getPosition(Long positionId) {
        return positionRepository.findById(positionId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (PositionService): Position with ID=" + positionId + " not found"));
    }
}
