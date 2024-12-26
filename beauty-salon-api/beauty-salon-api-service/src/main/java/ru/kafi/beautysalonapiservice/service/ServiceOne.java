package ru.kafi.beautysalonapiservice.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kafi.beautysalonapiservice.exception.NotFoundException;

public interface ServiceOne {
    static <T, R extends JpaRepository<T, Long>> T getEntityById(Long id, R repository) {
        String className = repository.getClass().getGenericSuperclass().getTypeName();
        System.out.println(className);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "API service (SalonServiceService): " + className + " with ID=" + id + " not found"));
    }
}
