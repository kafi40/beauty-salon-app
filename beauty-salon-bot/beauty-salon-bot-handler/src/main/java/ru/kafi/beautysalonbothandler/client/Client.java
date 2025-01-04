package ru.kafi.beautysalonbothandler.client;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import ru.kafi.beautysalonapicommon.dto.salon_service.InfoSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;

import java.util.List;

public interface Client {

    ResponseEntity<?> get(String path);

    Page<InfoEmployeeDto> getPage(String path);

    List<InfoSalonServiceDto> getAll(String path);

    ResponseEntity<?> post(String path, NewClientDto body);
}
