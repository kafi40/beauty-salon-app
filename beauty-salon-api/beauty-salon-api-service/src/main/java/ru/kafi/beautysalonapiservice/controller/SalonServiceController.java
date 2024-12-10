package ru.kafi.beautysalonapiservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.salon_service.InfoSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.NewSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.UpdateSalonServiceDto;
import ru.kafi.beautysalonapiservice.service.SalonServiceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SalonServiceController {
    private final SalonServiceService salonServiceService;

    @GetMapping("/services/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoSalonServiceDto getPublic(@PathVariable final Long serviceId) {
        log.info("API service (SalonServiceController): Get public service with ID={}", serviceId);
        return salonServiceService.get(serviceId);
    }

    @GetMapping("/services")
    @ResponseStatus(HttpStatus.OK)
    public List<InfoSalonServiceDto> getListPublic() {
        log.info("API service (SalonServiceController): Public get services");
        return salonServiceService.getAll();
    }

    @PostMapping("/admin/services")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoSalonServiceDto createAdmin(@RequestBody final NewSalonServiceDto newSalonService) {
        log.info("API service (SalonServiceController): Admin create new service={}", newSalonService);
        return salonServiceService.create(newSalonService);
    }

    @PatchMapping("/admin/services/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoSalonServiceDto updateAdmin(
            @PathVariable final Long serviceId,
            @RequestBody final UpdateSalonServiceDto updateSalonService) {
        log.info("API service (SalonServiceController): Admin update service={} with ID={}", updateSalonService, serviceId);
        return salonServiceService.update(serviceId, updateSalonService);
    }

    @DeleteMapping("/admin/services/{serviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable final Long serviceId) {
        log.info("API service (SalonServiceController): Admin delete service with ID={}", serviceId);
        salonServiceService.delete(serviceId);
    }
}
