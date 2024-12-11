package ru.kafi.beautysalonapigateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonapicommon.dto.user.UpdateUserDto;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.UpdateClientDto;
import ru.kafi.beautysalonapigateway.annotation.PositiveList;
import ru.kafi.beautysalonapigateway.client.UserClient;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserClient userClient;

    @GetMapping("/api/admin/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> adminGetClient(@Positive @PathVariable Long clientId, HttpServletRequest request) {
        log.info("API gateway (UserController): Admin get client with id={}", clientId);
        return userClient.get(request);
    }

    @GetMapping("/api/admin/employees")
    @ResponseStatus(HttpStatus.OK)
    public Page<?> adminGetEmployeesPage(
            @PositiveList @RequestParam(required = false) List<Long> positionIds,
            @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
            @Positive @RequestParam(defaultValue = "10") Integer size,
            HttpServletRequest request
    ) {
        log.info("API gateway (UserController): Admin get employees with param from={}, size={}", from, size);
        return userClient.getPage(request);
    }

    @PostMapping("/api/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody NewClientDto newClient, HttpServletRequest request) {
        log.info("API gateway (UserController): Public create clients {}", newClient);
        return userClient.create(request, newClient);
    }

    @PatchMapping("/api/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> patch(
            @Positive @PathVariable Long clientId,
            @Valid @RequestBody UpdateClientDto updateClient,
            HttpServletRequest request) {
        log.info("API gateway (UserController): Private patch client={} by ID={}", updateClient, clientId);
        return userClient.update(request, updateClient);
    }

    @DeleteMapping("/api/clients/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@Positive @PathVariable Long clientId,
                       HttpServletRequest request) {
        log.info("API gateway (UserController): Private delete client by ID={}", clientId);
        return userClient.delete(request);
    }
}
