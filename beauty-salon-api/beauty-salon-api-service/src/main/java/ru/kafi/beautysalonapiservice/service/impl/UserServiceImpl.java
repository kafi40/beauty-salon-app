package ru.kafi.beautysalonapiservice.service.impl;

import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.user.UpdateUserDto;
import ru.kafi.beautysalonapicommon.dto.user.client.InfoClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.UpdateClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.NewEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.UpdateEmployeeDto;
import ru.kafi.beautysalonapiservice.exception.ValueAlreadyUsedException;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;
import ru.kafi.beautysalonapiservice.repository.PositionRepository;
import ru.kafi.beautysalonapiservice.repository.UserRepository;
import ru.kafi.beautysalonapiservice.service.UserService;
import ru.kafi.beautysalonapiservice.service.entity.Position;
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
    private final PositionRepository positionRepository;
    private final UserMapper userMapper;

    @Override
    public InfoClientDto getClient(Long clientId) {
        User user = getUser(clientId);
        return userMapper.toClientDto(user);
    }

    @Override
    public Page<InfoEmployeeDto> getEmployeesPage(List<Long> positionIds, PageRequest page) {
        BooleanBuilder query = new BooleanBuilder();
        QUser qUser = QUser.user;
        if (positionIds != null)
            query.and(qUser.position.id.in(positionIds));
        Page<User> users = userRepository.findAll(query, page);
        return users.map(userMapper::toEmployeeDto);
    }

    @Override
    @Transactional
    public InfoClientDto createClient(NewClientDto newUser) {
        if (userRepository.existsByEmail(newUser.getEmail()))
            throw new ValueAlreadyUsedException("API service (UserService): The mail is already in use");
        User user = userMapper.toEntity(newUser);
        user = userRepository.save(user);
        return userMapper.toClientDto(user);
    }

    @Override
    @Transactional
    public InfoEmployeeDto createEmployee(NewEmployeeDto newEmployee) {
        if (userRepository.existsByEmail(newEmployee.getEmail()))
            throw new ValueAlreadyUsedException("API service (UserService): The mail is already in use");
        User user = userMapper.toEntity(newEmployee);
        Position position = getPosition(newEmployee.getPositionId());
        user.setPosition(position);
        user = userRepository.save(user);
        return userMapper.toEmployeeDto(user);
    }

    @Override
    @Transactional
    public InfoClientDto updateClient(Long clientId, UpdateClientDto updateClient) {
        User user = getUser(clientId);
        updateUser(user, updateClient);
        user = userRepository.save(user);
        return userMapper.toClientDto(user);
    }

    @Override
    @Transactional
    public InfoEmployeeDto updateEmployee(Long employeeId, UpdateEmployeeDto updateEmployee) {
        User user = getUser(employeeId);
        updateUser(user, updateEmployee);
        if (updateEmployee.getPositionId() != null) {
            Position position = getPosition(updateEmployee.getPositionId());
            user.setPosition(position);
        }
        user = userRepository.save(user);
        return userMapper.toEmployeeDto(user);
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    private void updateUser(User user, UpdateUserDto updateUser) {
        if (updateUser.getFirstName() != null)
            user.setFirstName(updateUser.getFirstName());
        if (updateUser.getLastName() != null)
            user.setLastName(updateUser.getLastName());
        if (updateUser.getMiddleName() != null)
            user.setMiddleName(updateUser.getMiddleName());
        if (updateUser.getEmail() != null) {
            if (userRepository.existsByEmail(updateUser.getEmail()))
                throw new ValueAlreadyUsedException("API service (UserService): The mail is already in use");
            user.setEmail(updateUser.getEmail());
        }
        if (updateUser.getGender() != null)
            user.setGender(updateUser.getGender());
        if (updateUser.getBirthday() != null)
            user.setBirthday(toDate(updateUser.getBirthday()));
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (UserService): User with ID=" + userId + " not found"));
    }

    private Position getPosition(Long positionId) {
        return positionRepository.findById(positionId)
                .orElseThrow(() -> new NotFoundException(
                        "API service (UserService): Position with ID=" + positionId + " not found"));
    }

    private Date toDate(String date) {
        return Date.valueOf(LocalDate.parse(date));
    }

    private Date toDate(LocalDate date) {
        return Date.valueOf(date);
    }
}
