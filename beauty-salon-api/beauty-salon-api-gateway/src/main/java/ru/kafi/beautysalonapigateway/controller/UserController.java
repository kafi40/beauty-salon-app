package ru.kafi.beautysalonapigateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.UpdateClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.NewEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.UpdateEmployeeDto;
import ru.kafi.beautysalonapigateway.annotation.PositiveList;
import ru.kafi.beautysalonapigateway.client.Client;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final Client userClient;

    @GetMapping("/api/admin/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> adminGetClient(
            @Positive @PathVariable final Long clientId,
            final HttpServletRequest request) {
        return userClient.get(request);
    }

    @GetMapping("/api/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> privateGetClient(
            @Positive @PathVariable final Long clientId,
            final HttpServletRequest request) {
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
        return userClient.getPage(request);
    }

    @PostMapping("/api/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> publicCreateClient(
            @Valid @RequestBody final NewClientDto newClient,
            final HttpServletRequest request) {
        return userClient.create(request, newClient);
    }

    @PostMapping("/api/admin/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> adminCreateEmployee(
            @Valid @RequestBody final NewEmployeeDto newEmployee,
            final HttpServletRequest request) {
        return userClient.create(request, newEmployee);
    }

    @PatchMapping("/api/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> privatePatchClient(
            @Positive @PathVariable final Long clientId,
            @Valid @RequestBody final UpdateClientDto updateClient,
            final HttpServletRequest request) {
        return userClient.update(request, updateClient);
    }

    @PatchMapping("/admin/employees/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> adminPatchEmployee(
            @Positive @PathVariable final Long employeeId,
            @Valid @RequestBody final UpdateEmployeeDto updateEmployee,
            final HttpServletRequest request) {
        return userClient.update(request, updateEmployee);
    }

    @DeleteMapping("/api/clients/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> privateDelete(
            @Positive @PathVariable final Long clientId,
            final HttpServletRequest request) {
        return userClient.delete(request);
    }
}
