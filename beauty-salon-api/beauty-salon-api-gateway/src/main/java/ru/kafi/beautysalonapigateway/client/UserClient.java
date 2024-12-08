package ru.kafi.beautysalonapigateway.client;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonapicommon.dto.user.UpdateUserDto;

import java.util.List;

public interface UserClient {
    ResponseEntity<?> get(HttpServletRequest request);

    List<ResponseEntity<?>> getAll(HttpServletRequest request);

    ResponseEntity<?> create(HttpServletRequest request, NewUserDto newUser);

    ResponseEntity<?> update(HttpServletRequest request, UpdateUserDto updateUser);

    void delete(HttpServletRequest request);
}
