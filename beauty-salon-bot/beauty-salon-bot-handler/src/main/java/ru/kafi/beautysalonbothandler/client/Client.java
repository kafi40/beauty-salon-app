package ru.kafi.beautysalonbothandler.client;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;

public interface Client {

    ResponseEntity<?> get(String path);

    Page<InfoEmployeeDto> getAll(String path);

    ResponseEntity<?> post(String path, NewClientDto body);
}
