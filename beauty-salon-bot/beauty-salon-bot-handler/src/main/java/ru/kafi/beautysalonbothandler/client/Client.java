package ru.kafi.beautysalonbothandler.client;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;

import java.util.List;

public interface Client<T> {

    ResponseEntity<?> get(String path);

    Page<T> getPage(String path);

    List<T> getAll(String path);

    ResponseEntity<?> post(String path, NewClientDto body);
}
