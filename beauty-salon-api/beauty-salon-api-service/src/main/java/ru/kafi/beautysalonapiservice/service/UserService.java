package ru.kafi.beautysalonapiservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import ru.kafi.beautysalonapicommon.dto.user.*;

import java.util.List;

public interface UserService {
    FullInfoUserDto get(Long userId);

    Page<InfoUserDto> getAll(List<Long> positionIds, PageRequest page);

    InfoClientDto create(NewUserDto newUser);

    InfoClientDto update(Long userId, UpdateUserDto updateUser);

    void delete(Long userId);
}
