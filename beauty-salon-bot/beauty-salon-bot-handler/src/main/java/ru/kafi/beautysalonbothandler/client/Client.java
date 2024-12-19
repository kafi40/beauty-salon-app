package ru.kafi.beautysalonbothandler.client;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface Client {

    ResponseEntity<?> get(String path);

    Page<?> getAll(String path);

    ResponseEntity<?> post(String path, Object body);
}
