package ru.kafi.beautysalonapicommon.dto.salon_service;

import jakarta.validation.constraints.Min;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateSalonServiceDto {
    @Size(min = 1, max = 64, message = "The number of characters allowed is from 1 to 64")
    private String name;
    @Size(min = 20, max = 2000, message = "The number of characters allowed is from 1 to 2000")
    private String description;
    @Min(value = 100, message = "The price be greater than 100")
    private Double price;
    @Min(value = 30, message = "The duration be greater than 30 minutes")
    private Integer duration;
}
