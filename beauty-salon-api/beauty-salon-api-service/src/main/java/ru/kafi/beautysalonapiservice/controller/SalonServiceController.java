package ru.kafi.beautysalonapiservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.salon_service.InfoSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.NewSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.salon_service.UpdateSalonServiceDto;
import ru.kafi.beautysalonapiservice.service.SalonServiceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SalonServiceController {
    private final SalonServiceService salonServiceService;

    @GetMapping("/services/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoSalonServiceDto getPublic(@PathVariable final Long serviceId) {
        return salonServiceService.get(serviceId);
    }

    @GetMapping("/services")
    @ResponseStatus(HttpStatus.OK)
    public List<InfoSalonServiceDto> getListPublic() {
        return salonServiceService.getAll();
    }

    @PostMapping("/admin/services")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoSalonServiceDto createAdmin(@RequestBody final NewSalonServiceDto newSalonService) {
        return salonServiceService.create(newSalonService);
    }

    @PatchMapping("/admin/services/{serviceId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoSalonServiceDto updateAdmin(
            @PathVariable final Long serviceId,
            @RequestBody final UpdateSalonServiceDto updateSalonService) {
        return salonServiceService.update(serviceId, updateSalonService);
    }

    @DeleteMapping("/admin/services/{serviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable final Long serviceId) {
        salonServiceService.delete(serviceId);
    }
}
