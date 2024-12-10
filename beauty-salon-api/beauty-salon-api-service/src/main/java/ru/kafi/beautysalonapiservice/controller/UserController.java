package ru.kafi.beautysalonapiservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.kafi.beautysalonapicommon.dto.user.*;
import ru.kafi.beautysalonapicommon.dto.user.client.InfoClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.EmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.NewEmployeeDto;
import ru.kafi.beautysalonapiservice.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("/admin/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public FullInfoUserDto adminGet(@PathVariable Long userId) {
        log.info("API service (UserController): Admin get user with param ids={}", userId);
        return userService.get(userId);
    }

    @GetMapping("/admin/users")
    @ResponseStatus(HttpStatus.OK)
    public Page<InfoUserDto> adminGetPage(
            @RequestParam(required = false) List<Long> positionIds,
            @RequestParam(defaultValue = "0") int from,
            @RequestParam(defaultValue = "10") int size
            ) {
        log.info("API service (UserController): Admin get users with param from={}, size={}", from, size);
        return userService.getAll(positionIds, PageRequest.of(from, size));
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public InfoClientDto publicCreate(@RequestBody NewUserDto newUser) {
        log.info("API service (UserController): Public create user={}", newUser);
        return userService.create(newUser);
    }

    @PostMapping("/admin/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDto privateCreate(@RequestBody EmployeeDto newEmployee) {
        log.info("API service (UserController): Public create employee={}", newEmployee);
        return userService.create(newEmployee);
    }

    @PatchMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public InfoClientDto privatePatch(@PathVariable Long userId, @RequestBody UpdateUserDto updateUser) {
        log.info("API service (UserController): Private patch user={} by ID={}", updateUser, userId);
        return userService.update(userId, updateUser);
    }

    @DeleteMapping("/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void privateDelete(@PathVariable Long userId) {
        log.info("API service (UserController): Private delete by ID={}", userId);
        userService.delete(userId);
    }
}
