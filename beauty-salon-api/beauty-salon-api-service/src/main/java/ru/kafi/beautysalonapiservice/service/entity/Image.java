package ru.kafi.beautysalonapiservice.service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "path", nullable = false, length = 512)
    private String path;
    @Column(name = "title", nullable = false, length = 64)
    private String title;
}
