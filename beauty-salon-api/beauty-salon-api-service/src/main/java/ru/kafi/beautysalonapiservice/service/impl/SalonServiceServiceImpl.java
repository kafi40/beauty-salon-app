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
        log.info("API service (SalonServiceService): Try get()");
        SalonService salonService = getSalonService(serviceId);
        log.info("API service (SalonServiceService): Finish get()");
        return salonServiceMapper.toDto(salonService);
    }

    @Override
    public List<InfoSalonServiceDto> getAll() {
        log.info("API service (SalonServiceService): Try getAll()");
        List<SalonService> salonServiceList = salonServiceRepository.findAll();
        log.info("API service (SalonServiceService): Finish getAll()");
        return salonServiceList.stream()
                .map(salonServiceMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public InfoSalonServiceDto create(NewSalonServiceDto newSalonService) {
        log.info("API service (SalonServiceService): Try create()");
        SalonService salonService = salonServiceMapper.toEntity(newSalonService);
        if (salonServiceRepository.existsByNameWithinIgnoreCase(newSalonService.getName()))
            throw new ValueAlreadyUsedException("API service (SalonServiceService): The name is already in use");
        salonService = salonServiceRepository.save(salonService);
        log.info("API service (SalonServiceService): Finish create()");
        return salonServiceMapper.toDto(salonService);
    }

    @Override
    @Transactional
    public InfoSalonServiceDto update(Long serviceId, UpdateSalonServiceDto updateSalonService) {
        log.info("API service (SalonServiceService): Try update()");
        SalonService salonService = getSalonService(serviceId);
        if (updateSalonService.getName() != null) {
            if (salonServiceRepository.existsByNameWithinIgnoreCase(updateSalonService.getName()))
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
        log.info("API service (SalonServiceService): Finish update()");
        return salonServiceMapper.toDto(salonService);
    }

    @Override
    @Transactional
    public void delete(Long serviceId) {
        log.info("API service (SalonServiceService): Try delete()");
        salonServiceRepository.deleteById(serviceId);
        log.info("API service (SalonServiceService): Finish delete()");
    }

    private SalonService getSalonService(Long serviceId) {
        return salonServiceRepository.findById(serviceId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (SalonServiceService): Service with ID=" + serviceId + " not found"));
    }
}
