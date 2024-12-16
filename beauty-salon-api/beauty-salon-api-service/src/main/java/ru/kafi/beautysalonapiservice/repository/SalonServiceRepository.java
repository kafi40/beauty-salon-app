package ru.kafi.beautysalonapiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kafi.beautysalonapiservice.service.entity.SalonService;

@Repository
public interface SalonServiceRepository extends JpaRepository<SalonService, Long> {
    boolean existsByNameWithinIgnoreCase(String name);
}
