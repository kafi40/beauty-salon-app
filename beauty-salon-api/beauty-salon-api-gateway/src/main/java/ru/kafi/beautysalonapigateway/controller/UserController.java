package ru.kafi.beautysalonapigateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonapicommon.dto.user.UpdateUserDto;
import ru.kafi.beautysalonapigateway.annotation.PositiveList;
import ru.kafi.beautysalonapigateway.client.UserClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserClient userClient;

    @GetMapping("api/admin/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> get(@Positive @PathVariable Long userId, HttpServletRequest request) {
        log.info("API gateway (UserController): Get user with param ids={}", userId);
        return userClient.get(request);
    }

    @GetMapping("api/admin/users")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseEntity<?>> getAll(
            @PositiveList @RequestParam(required = false) List<Long> positionIds,
            @PositiveOrZero @RequestParam(required = false, defaultValue = "0") Integer from,
            @Positive @RequestParam(required = false, defaultValue = "10") Integer size,
            HttpServletRequest request
    ) {
        log.info("API gateway (UserController): Get user with param from={}, size={}", from, size);
        if (!request.getParameterMap().containsKey("from"))
            request.getParameterMap().put("from", new String[]{String.valueOf(from)});

        if (!request.getParameterMap().containsKey("size"))
            request.getParameterMap().put("size", new String[]{String.valueOf(size)});

        return userClient.getAll(request);
    }

    @PostMapping("api/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody NewUserDto newUser, HttpServletRequest request) {
        log.info("API gateway (UserController): Create user {}", newUser);
        return userClient.create(request, newUser);
    }

    @PatchMapping("api/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> patch(
            @Positive @PathVariable Long userId,
            @Valid @RequestBody UpdateUserDto updateUser,
            HttpServletRequest request) {
        log.info("API gateway (UserController): Patch user={} by ID={}", updateUser, userId);
        return userClient.update(request, updateUser);
    }

    @DeleteMapping("api/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @PathVariable Long userId,
                       HttpServletRequest request) {
        log.info("API gateway (UserController): Delete by ID={}", userId);
        userClient.delete(request);
    }
}
