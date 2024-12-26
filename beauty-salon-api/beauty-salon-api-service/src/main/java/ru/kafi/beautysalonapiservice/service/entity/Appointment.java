package ru.kafi.beautysalonapiservice.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.kafi.beautysalonapicommon.enums.Status;

import java.sql.Timestamp;

@Entity
@Table(name = "appointments")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @JoinColumn(name = "client_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User client;
    @JoinColumn(name = "employee_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User employee;
    @JoinColumn(name = "service_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private SalonService salonService;
    @Column(name = "registered_on", nullable = false)
    private Timestamp registeredOn;
    @Column(name = "registered_end", nullable = false)
    private Timestamp registeredEnd;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
}
