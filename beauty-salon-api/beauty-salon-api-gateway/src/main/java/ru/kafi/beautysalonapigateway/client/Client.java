package ru.kafi.beautysalonapigateway.client;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Client {
    ResponseEntity<?> get(HttpServletRequest request);

    Page<?> getPage(HttpServletRequest request);

    ResponseEntity<List<?>> getList(HttpServletRequest request);

    <T> ResponseEntity<?> create(HttpServletRequest request, T newDto);

    <T> ResponseEntity<?> update(HttpServletRequest request, T updateDto);

    ResponseEntity<?> delete(HttpServletRequest request);
}
