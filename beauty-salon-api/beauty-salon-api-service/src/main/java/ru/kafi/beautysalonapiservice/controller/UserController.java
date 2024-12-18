package ru.kafi.beautysalonapiservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.user.client.InfoClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.UpdateClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.NewEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.UpdateEmployeeDto;
import ru.kafi.beautysalonapiservice.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/admin/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoClientDto adminGetClient(@PathVariable final Long clientId) {
        return userService.getClient(clientId);
    }

    @GetMapping("/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoClientDto privateGetClient(@PathVariable final Long clientId) {
        return userService.getClient(clientId);
    }

    @GetMapping("/admin/employees")
    @ResponseStatus(HttpStatus.OK)
    public Page<InfoEmployeeDto> adminGetEmployeesPage(
            @RequestParam(required = false) final List<Long> positionIds,
            @RequestParam(defaultValue = "0") final int from,
            @RequestParam(defaultValue = "10") final int size
            ) {
        return userService.getEmployeesPage(positionIds, PageRequest.of(from, size));
    }

    @PostMapping("/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoClientDto publicCreateClient(@RequestBody final NewClientDto newClient) {
        return userService.createClient(newClient);
    }

    @PostMapping("/admin/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoEmployeeDto adminCreateEmployee(@RequestBody final NewEmployeeDto newEmployee) {
        return userService.createEmployee(newEmployee);
    }

    @PatchMapping("/clients/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoClientDto privatePatchClient(
            @PathVariable final Long clientId,
            @RequestBody final UpdateClientDto updateClient) {
        return userService.updateClient(clientId, updateClient);
    }

    @PatchMapping("/admin/employees/{employeeId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoEmployeeDto adminPatchEmployee(
            @PathVariable final Long employeeId,
            @RequestBody final UpdateEmployeeDto updateEmployee) {
        return userService.updateEmployee(employeeId, updateEmployee);
    }

    @DeleteMapping("/clients/{clientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void privateDelete(@PathVariable final Long clientId) {
        userService.delete(clientId);
    }
}
