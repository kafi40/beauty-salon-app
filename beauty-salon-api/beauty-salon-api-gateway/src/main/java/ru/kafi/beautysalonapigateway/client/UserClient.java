package ru.kafi.beautysalonapigateway.client;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonapicommon.dto.user.UpdateUserDto;

public interface UserClient {
    ResponseEntity<?> get(HttpServletRequest request);

    Page<?> getPage(HttpServletRequest request);

    ResponseEntity<?> create(HttpServletRequest request, NewUserDto newUser);

    ResponseEntity<?> update(HttpServletRequest request, UpdateUserDto updateUser);

    ResponseEntity<?> delete(HttpServletRequest request);
}
