package ru.kafi.beautysalonapiservice.service.impl;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.user.*;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;
import ru.kafi.beautysalonapiservice.repository.UserRepository;
import ru.kafi.beautysalonapiservice.service.UserService;
import ru.kafi.beautysalonapiservice.service.entity.QUser;
import ru.kafi.beautysalonapiservice.service.entity.User;
import ru.kafi.beautysalonapiservice.service.mapper.UserMapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public FullInfoUserDto get(Long userId) {
        log.info("API service (UserService): Try get()");
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("API service (UserController): User with ID=" + userId + " not found"));
        log.info("API service (UserService): Finish get()");
        return userMapper.toFullDto(user);
    }

    @Override
    public Page<InfoUserDto> getAll(List<Long> positionIds, PageRequest page) {
        log.info("API service (UserService): Try getAll()");
        BooleanBuilder query = new BooleanBuilder();
        QUser qUser = QUser.user;
        if (positionIds != null)
            query.and(qUser.position.id.in(positionIds));
        Page<User> users = userRepository.findAll(query, page);
        log.info("API service (UserService): Finish getAll().");
        return users.map(userMapper::toDto);
    }

    @Override
    public InfoClientDto create(NewUserDto newUser) {
        log.info("API service (UserService): Try create()");
        User user = userMapper.toEntity(newUser);
        user = userRepository.save(user);
        log.info("API service (UserService): Finish create()");
        return userMapper.toClientDto(user);
    }

    @Override
    public InfoClientDto update(Long userId, UpdateUserDto updateUser) {
        log.info("API service (UserService): Try modify()");
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("API service (UserController): User with ID=" + userId + " not found"));
        if (updateUser.getFirstName() != null)
            user.setFirstName(updateUser.getFirstName());
        if (updateUser.getLastName() != null)
            user.setLastName(updateUser.getLastName());
        if (updateUser.getMiddleName() != null)
            user.setMiddleName(updateUser.getMiddleName());
        if (updateUser.getEmail() != null)
            user.setEmail(updateUser.getEmail());
        if (updateUser.getGender() != null)
            user.setGender(updateUser.getGender());
        if (updateUser.getBirthday() != null)
            user.setBirthday(toDate(updateUser.getBirthday()));
        user = userRepository.save(user);
        log.info("API service (UserService): Finish modify()");
        return userMapper.toClientDto(user);
    }

    @Override
    public void delete(Long userId) {
        log.info("API service (UserService): Try delete()");
        userRepository.deleteById(userId);
        log.info("API service (UserService): Finish delete()");
    }

    private Date toDate(String date) {
        return Date.valueOf(LocalDate.parse(date));
    }
}
