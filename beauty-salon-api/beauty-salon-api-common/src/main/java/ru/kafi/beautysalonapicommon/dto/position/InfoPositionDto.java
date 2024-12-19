package ru.kafi.beautysalonapicommon.dto.position;

import lombok.Data;

@Data
public class InfoPositionDto {
    private Long id;
    private String name;
    private Double minSalary;
    private Double maxSalary;
}
