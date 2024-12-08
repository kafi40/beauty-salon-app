package ru.kafi.beautysalonapiservice.service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "positions")
@Data
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Column(name = "min_salary", nullable = false)
    private Double minSalary;
    @Column(name = "max_salary", nullable = false)
    private Double maxSalary;
}
