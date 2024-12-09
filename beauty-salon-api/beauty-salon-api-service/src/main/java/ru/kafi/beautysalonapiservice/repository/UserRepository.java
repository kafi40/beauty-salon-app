package ru.kafi.beautysalonapiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kafi.beautysalonapiservice.service.entity.User;

@ResponseStatus
public interface UserRepository extends JpaRepository<User, Long>, QuerydslPredicateExecutor<User> {
}
