package ru.kafi.beautysalonapiservice.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.salon_service.InfoSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.NewSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.UpdateSalonServiceDto;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;
import ru.kafi.beautysalonapiservice.exception.ValueAlreadyUsedException;
import ru.kafi.beautysalonapiservice.repository.SalonServiceRepository;
import ru.kafi.beautysalonapiservice.service.SalonServiceService;
import ru.kafi.beautysalonapiservice.service.entity.SalonService;
import ru.kafi.beautysalonapiservice.service.mapper.SalonServiceMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalonServiceServiceImpl implements SalonServiceService {
    private final SalonServiceRepository salonServiceRepository;
    private final SalonServiceMapper salonServiceMapper;

    @Override
    public InfoSalonServiceDto get(Long serviceId) {
        SalonService salonService = getSalonService(serviceId);
        return salonServiceMapper.toDto(salonService);
    }

    @Override
    public List<InfoSalonServiceDto> getAll() {
        List<SalonService> salonServiceList = salonServiceRepository.findAll();
        return salonServiceList.stream()
                .map(salonServiceMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public InfoSalonServiceDto create(NewSalonServiceDto newSalonService) {
        SalonService salonService = salonServiceMapper.toEntity(newSalonService);
        if (salonServiceRepository.existsByNameEqualsIgnoreCase(newSalonService.getName()))
            throw new ValueAlreadyUsedException("API service (SalonServiceService): The name is already in use");
        salonService = salonServiceRepository.save(salonService);
        return salonServiceMapper.toDto(salonService);
    }

    @Override
    @Transactional
    public InfoSalonServiceDto update(Long serviceId, UpdateSalonServiceDto updateSalonService) {
        SalonService salonService = getSalonService(serviceId);
        if (updateSalonService.getName() != null) {
            if (salonServiceRepository.existsByNameEqualsIgnoreCase(updateSalonService.getName()))
                throw new ValueAlreadyUsedException("API service (SalonServiceService): The name is already in use");
            salonService.setName(updateSalonService.getName());
        }
        if (updateSalonService.getDescription() != null)
            salonService.setDescription(updateSalonService.getDescription());
        if (updateSalonService.getPrice() != null)
            salonService.setPrice(updateSalonService.getPrice());
        if (updateSalonService.getDuration() != null)
            salonService.setDuration(updateSalonService.getDuration());
        salonService = salonServiceRepository.save(salonService);
        return salonServiceMapper.toDto(salonService);
    }

    @Override
    @Transactional
    public void delete(Long serviceId) {
        salonServiceRepository.deleteById(serviceId);
    }

    private SalonService getSalonService(Long serviceId) {
        return salonServiceRepository.findById(serviceId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (SalonServiceService): Service with ID=" + serviceId + " not found"));
    }
}
