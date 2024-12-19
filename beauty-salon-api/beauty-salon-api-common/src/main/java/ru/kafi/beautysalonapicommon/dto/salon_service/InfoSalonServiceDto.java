package ru.kafi.beautysalonapicommon.dto.salon_service;

import lombok.Data;

@Data
public class InfoSalonServiceDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer duration;
}
