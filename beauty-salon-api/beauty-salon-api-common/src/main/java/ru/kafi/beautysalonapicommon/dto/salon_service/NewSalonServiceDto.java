package ru.kafi.beautysalonapicommon.dto.salon_service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewSalonServiceDto {
    @NotBlank
    @Size(min = 1, max = 64)
    private String name;
    @NotBlank
    @Size(min = 20, max = 2000)
    private String description;
    @Positive
    private Double price;
    @Positive
    private Integer duration;
}
