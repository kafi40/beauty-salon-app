package ru.kafi.beautysalonapiservice.service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.DayOfWeek;

@Entity
@Table(name = "work_schedule")
@Getter
@Setter
@NoArgsConstructor
public class WorkSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", nullable = false, length = 16)
    private String name;
    private DayOfWeek dayOfWeek;
    @Column(name = "start_working", nullable = false)
    private Time startWorking;
    @Column(name = "end_working", nullable = false)
    private Time endWorking;
    @Column(name = "is_weekend", nullable = false)
    private Boolean isWeekend;
}
