package ru.kafi.beautysalonapiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kafi.beautysalonapiservice.service.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment AS a " +
            "WHERE a.employee.id = ?1 AND " +
            "a.registeredOn BETWEEN ?2 AND ?3")
    List<Appointment> findFreeSlot(Long employeeId, LocalDateTime start, LocalDateTime end);
}
