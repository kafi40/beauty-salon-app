package ru.kafi.beautysalonapiservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.position.InfoPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.NewPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.ShortPositionDto;
import ru.kafi.beautysalonapicommon.dto.position.UpdatePositionDto;
import ru.kafi.beautysalonapiservice.service.PositionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PositionController {
    private final PositionService positionService;

    @GetMapping("/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoPositionDto adminGet(@PathVariable final Long positionId) {
        log.info("API service (PositionController): Admin get position with ID={}", positionId);
        return positionService.get(positionId);
    }

    @GetMapping("/admin/positions")
    @ResponseStatus(HttpStatus.OK)
    public List<ShortPositionDto> adminGetList() {
        log.info("API service (PositionController): Admin get positions");
        return positionService.getAll();
    }

    @PostMapping("/admin/positions")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoPositionDto adminCreate(@RequestBody final NewPositionDto newPosition) {
        log.info("API service (PositionController): Admin create position={}", newPosition);
        return positionService.create(newPosition);
    }

    @PatchMapping("/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoPositionDto adminUpdate(
            @PathVariable final Long positionId,
            @RequestBody final UpdatePositionDto updatePosition) {
        log.info("API service (PositionController): Admin update position={} with ID={}", updatePosition, positionId);
        return positionService.update(positionId, updatePosition);
    }

    @DeleteMapping("/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adminDelete(@PathVariable final Long positionId) {
        log.info("API service (PositionController): Admin delete position with ID={}", positionId);
        positionService.delete(positionId);
    }
}
