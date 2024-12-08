package ru.kafi.beautysalonapiservice.service;

import ru.kafi.beautysalonapicommon.dto.user.*;

import java.util.List;

public interface UserService {
    FullInfoUserDto get(Long userId);

    List<InfoUserDto> getAll(List<Long> positionIds, int from, int size);

    InfoClientDto create(NewUserDto newUser);

    InfoClientDto update(Long userId, UpdateUserDto updateUser);

    void delete(Long userId);
}
