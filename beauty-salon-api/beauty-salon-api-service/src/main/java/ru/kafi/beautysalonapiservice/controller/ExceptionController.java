package ru.kafi.beautysalonapiservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kafi.beautysalonapicommon.dto.ApiErrorDto;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorDto notFoundException(final NotFoundException e) {
        log.info("Not found exception - {}", e.getMessage(), e);
        return new ApiErrorDto(
                HttpStatus.NOT_FOUND.toString(),
                "Not found error",
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorDto handleUnexpected(final Exception e) {
        log.warn("unexpected error");
        return new ApiErrorDto(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Not found error",
                e.getMessage(),
                LocalDateTime.now()
        );
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> valueAlreadyUsedException(final ValueAlreadyUsedException e) {
        return Map.of("error:", e.getMessage());
    }
}
