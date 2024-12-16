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
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.UpdateClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.NewEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.UpdateEmployeeDto;
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
    public ResponseEntity<?> adminGetClient(
            @Positive @PathVariable final Long clientId,
            final HttpServletRequest request) {
        log.info("API gateway (UserController): Admin get client with id={}", clientId);
        return userClient.get(request);
    }

    @GetMapping("/api/admin/employees")
    @ResponseStatus(HttpStatus.OK)
    public Page<?> adminGetEmployeesPage(
            @PositiveList @RequestParam(required = false) final List<Long> positionIds,
            @PositiveOrZero @RequestParam(defaultValue = "0") final Integer from,
            @Positive @RequestParam(defaultValue = "10") final Integer size,
            HttpServletRequest request
    ) {
        log.info("API gateway (UserController): Admin get employees with param positionIds={}, from={}, size={}", positionIds, from, size);
        return userClient.getPage(request);
    }

    @PostMapping("/api/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> publicCreateClient(
            @Valid @RequestBody final NewClientDto newClient,
            final HttpServletRequest request) {
        log.info("API gateway (UserController): Public create clients {}", newClient);
        return userClient.create(request, newClient);
    }

    @PostMapping("/api/admin/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adminCreateEmployee(
            @Valid @RequestBody final NewEmployeeDto newEmployee,
            final HttpServletRequest request) {
        log.info("API gateway (UserController): Admin create employee={}", newEmployee);
        return userClient.create(request, newEmployee);
    }

    @PatchMapping("/api/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> privatePatchClient(
            @Positive @PathVariable final Long clientId,
            @Valid @RequestBody final UpdateClientDto updateClient,
            final HttpServletRequest request) {
        log.info("API gateway (UserController): Private patch client={} by ID={}", updateClient, clientId);
        return userClient.update(request, updateClient);
    }

    @PatchMapping("/admin/employees/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> adminPatchEmployee(
            @Positive @PathVariable final Long employeeId,
            @Valid @RequestBody final UpdateEmployeeDto updateEmployee,
            final HttpServletRequest request) {
        log.info("API gateway (UserController): Admin patch employee={} by ID={}", updateEmployee, employeeId);
        return userClient.update(request, updateEmployee);
    }

    @DeleteMapping("/api/clients/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> privateDelete(
            @Positive @PathVariable final Long clientId,
            final HttpServletRequest request) {
        log.info("API gateway (UserController): Private delete client by ID={}", clientId);
        return userClient.delete(request);
    }
}
