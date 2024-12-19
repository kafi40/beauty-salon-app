package ru.kafi.beautysalonapiservice.controller;

import lombok.RequiredArgsConstructor;
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
public class PositionController {
    private final PositionService positionService;

    @GetMapping("/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoPositionDto adminGet(@PathVariable final Long positionId) {
        return positionService.get(positionId);
    }

    @GetMapping("/admin/positions")
    @ResponseStatus(HttpStatus.OK)
    public List<ShortPositionDto> adminGetList() {
        return positionService.getAll();
    }

    @PostMapping("/admin/positions")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoPositionDto adminCreate(@RequestBody final NewPositionDto newPosition) {
        return positionService.create(newPosition);
    }

    @PatchMapping("/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoPositionDto adminUpdate(
            @PathVariable final Long positionId,
            @RequestBody final UpdatePositionDto updatePosition) {
        return positionService.update(positionId, updatePosition);
    }

    @DeleteMapping("/admin/positions/{positionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void adminDelete(@PathVariable final Long positionId) {
        positionService.delete(positionId);
    }
}
