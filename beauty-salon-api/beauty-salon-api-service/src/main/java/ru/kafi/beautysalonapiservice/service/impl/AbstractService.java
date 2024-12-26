package ru.kafi.beautysalonapiservice.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;

@Slf4j
public abstract class AbstractService {
    protected <T, R extends JpaRepository<T, Long>> T getEntityById(Long id, R repository) {
        String className = repository.getClass().getGenericSuperclass().getTypeName();
        System.out.println(className);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "API service (SalonServiceService): " + className + " with ID=" + id + " not found"));
    }
}
