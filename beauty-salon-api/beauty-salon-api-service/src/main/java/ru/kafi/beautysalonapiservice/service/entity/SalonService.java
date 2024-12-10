package ru.kafi.beautysalonapiservice.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "salon_services")
@Getter
@Setter
public class SalonService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, length = 64, unique = true)
    private String name;
    @Column(name = "description", nullable = false, length = 2000)
    private String description;
    @Column(name = "price",  nullable = false)
    private Double price;
    @Column(name = "duration", nullable = false)
    private Integer duration;
}
