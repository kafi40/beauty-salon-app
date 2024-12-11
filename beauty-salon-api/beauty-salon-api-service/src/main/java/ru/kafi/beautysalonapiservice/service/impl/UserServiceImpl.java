package ru.kafi.beautysalonapiservice.service.impl;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.kafi.beautysalonapicommon.dto.user.client.InfoClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.client.UpdateClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.NewEmployeeDto;
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
        log.info("API service (UserService): Try getClient()");
        User user =  userRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("API service (UserController): User with ID=" + clientId + " not found"));
        log.info("API service (UserService): Finish getClient()");
        return userMapper.toClientDto(user);
    }

    @Override
    public Page<InfoEmployeeDto> getEmployeesPage(List<Long> positionIds, PageRequest page) {
        log.info("API service (UserService): Try getEmployeesPage()");
        BooleanBuilder query = new BooleanBuilder();
        QUser qUser = QUser.user;
        if (positionIds != null)
            query.and(qUser.position.id.in(positionIds));
        Page<User> users = userRepository.findAll(query, page);
        log.info("API service (UserService): Finish getEmployeesPage().");
        return users.map(userMapper::toEmployeeDto);
    }

    @Override
    public InfoClientDto createClient(NewClientDto newUser) {
        log.info("API service (UserService): Try createClient()");
        User user = userMapper.toEntity(newUser);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
        log.info("API service (UserService): Finish createClient()");
        return userMapper.toClientDto(user);
    }

    @Override
    public InfoEmployeeDto createEmployee(NewEmployeeDto newEmployee) {
        log.info("API service (UserService): Try createEmployee()");
        User user = userMapper.toEntity(newEmployee);
        Position position = positionRepository.findById(newEmployee.getPositionId())
                .orElseThrow(() -> new NotFoundException(
                        "API service (UserController): Position with ID=" + newEmployee.getPositionId() + " not found"));
        user.setPosition(position);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
        log.info("API service (UserService): Finish createEmployee()");
        return userMapper.toEmployeeDto(user);
    }

    @Override
    public InfoClientDto updateClient(Long clientId, UpdateClientDto updateClient) {
        log.info("API service (UserService): Try updateClient()");
        User user = userRepository.findById(clientId)
                .orElseThrow(() -> new NotFoundException("API service (UserController): Client with ID=" + clientId + " not found"));
        if (updateClient.getFirstName() != null)
            user.setFirstName(updateClient.getFirstName());
        if (updateClient.getLastName() != null)
            user.setLastName(updateClient.getLastName());
        if (updateClient.getMiddleName() != null)
            user.setMiddleName(updateClient.getMiddleName());
        if (updateClient.getEmail() != null)
            user.setEmail(updateClient.getEmail());
        if (updateClient.getGender() != null)
            user.setGender(updateClient.getGender());
        if (updateClient.getBirthday() != null)
            user.setBirthday(toDate(updateClient.getBirthday()));
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            System.out.println(e.getMessage());
        }
        log.info("API service (UserService): Finish updateClient()");
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
