package ru.kafi.beautysalonapiservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.kafi.beautysalonapicommon.dto.user.client.InfoClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.UpdateClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.NewEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.UpdateEmployeeDto;

import java.util.List;

public interface UserService {
    InfoClientDto getClient(Long clientId);

    Page<InfoEmployeeDto> getEmployeesPage(List<Long> positionIds, PageRequest page);

    InfoClientDto createClient(NewClientDto newClient);

    InfoEmployeeDto createEmployee(NewEmployeeDto newEmployee);

    InfoClientDto updateClient(Long clientId, UpdateClientDto updateClient);

    InfoEmployeeDto updateEmployee(Long employeeId, UpdateEmployeeDto updateEmployee);

    void delete(Long userId);
}
