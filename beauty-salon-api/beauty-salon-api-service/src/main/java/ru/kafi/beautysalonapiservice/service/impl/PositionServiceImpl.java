package ru.kafi.beautysalonapiservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.position.InfoPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.NewPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.ShortPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.UpdatePositionDto;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;
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
        Position position =  positionRepository.findById(positionId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (PositionService): Position with ID=" + positionId + " not found"));
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
    public InfoPositionDto create(NewPositionDto newPosition) {
        log.info("API service (PositionService): Try create()");
        Position position = positionMapper.toEntity(newPosition);
        try {
            position = positionRepository.save(position);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
        log.info("API service (PositionService): Finish create()");
        return positionMapper.toDto(position);
    }

    @Override
    public InfoPositionDto update(Long positionId, UpdatePositionDto updatePosition) {
        log.info("API service (PositionService): Try update()");
        Position position = positionRepository.findById(positionId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (PositionService): Position with ID=" + positionId + " not found"));
        if (updatePosition.getName() != null)
            position.setName(updatePosition.getName());
        if (updatePosition.getMinSalary() != null)
            position.setMinSalary(updatePosition.getMinSalary());
        if (updatePosition.getMaxSalary() != null)
            position.setMaxSalary(updatePosition.getMaxSalary());
        try {
            position = positionRepository.save(position);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
        log.info("API service (PositionService): Finish update()");
        return positionMapper.toDto(position);
    }

    @Override
    public void delete(Long positionId) {
        log.info("API service (PositionService): Try to delete()");
        positionRepository.deleteById(positionId);
        log.info("API service (PositionService): Finish delete()");
    }
}
