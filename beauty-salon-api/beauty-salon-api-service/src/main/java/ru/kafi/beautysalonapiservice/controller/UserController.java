package ru.kafi.beautysalonapiservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.user.*;
import ru.kafi.beautysalonapiservice.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/admin/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public FullInfoUserDto get(@PathVariable Long userId) {
        log.info("API service (UserController): Get user with param ids={}", userId);
        return userService.get(userId);
    }

    @GetMapping("/admin/users")
    @ResponseStatus(HttpStatus.OK)
    public Page<InfoUserDto> getAll(
            @RequestParam(required = false) List<Long> positionIds,
            @RequestParam(required = false, defaultValue = "0") int from,
            @RequestParam(required = false, defaultValue = "10") int size
            ) {
        log.info("API service (UserController): Get user with param from={}, size={}", from, size);
        return userService.getAll(positionIds, from, size);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoClientDto create(@RequestBody NewUserDto newUser) {
        log.info("API service (UserController): Create user {}", newUser);
        return userService.create(newUser);
    }

    @PatchMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoClientDto patch(@PathVariable Long userId, @RequestBody UpdateUserDto updateUser) {
        log.info("API service (UserController): Patch user={} by ID={}", updateUser, userId);
        return userService.update(userId, updateUser);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long userId) {
        log.info("API service (UserController): Delete by ID={}", userId);
        userService.delete(userId);
    }
}
