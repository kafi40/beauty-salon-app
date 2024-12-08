package ru.kafi.beautysalonapiservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;

import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> notFountException(final NotFoundException e) {
        return Map.of("error:", e.getMessage());
    }
}
