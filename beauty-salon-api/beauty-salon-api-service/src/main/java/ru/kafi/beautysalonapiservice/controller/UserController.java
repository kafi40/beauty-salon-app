package ru.kafi.beautysalonapiservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.user.client.InfoClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.UpdateClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.NewEmployeeDto;
import ru.kafi.beautysalonapiservice.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/admin/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoClientDto adminGetClient(@PathVariable Long clientId) {
        log.info("API service (UserController): Admin get client with param id={}", clientId);
        return userService.getClient(clientId);
    }

    @GetMapping("/admin/employees")
    @ResponseStatus(HttpStatus.OK)
    public Page<InfoEmployeeDto> adminGetEmployeesPage(
            @RequestParam(required = false) List<Long> positionIds,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size
            ) {
        log.info("API service (UserController): Admin get employees with param from={}, size={}", from, size);
        return userService.getEmployeesPage(positionIds, PageRequest.of(from, size));
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoClientDto publicCreateClient(@RequestBody NewClientDto newClient) {
        log.info("API service (UserController): Public create client={}", newClient);
        return userService.createClient(newClient);
    }

    @PostMapping("/admin/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoEmployeeDto adminCreateEmployee(@RequestBody NewEmployeeDto newEmployee) {
        log.info("API service (UserController): Admin create employee={}", newEmployee);
        return userService.createEmployee(newEmployee);
    }

    @PatchMapping("/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoClientDto privatePatch(@PathVariable Long clientId, @RequestBody UpdateClientDto updateClient) {
        log.info("API service (UserController): Private patch client={} by ID={}", updateClient, clientId);
        return userService.updateClient(clientId, updateClient);
    }

    @DeleteMapping("/clients/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void privateDelete(@PathVariable Long clientId) {
        log.info("API service (UserController): Private delete client by ID={}", clientId);
        userService.delete(clientId);
    }
}
