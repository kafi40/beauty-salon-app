package ru.kafi.beautysalonapiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kafi.beautysalonapiservice.service.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
}
