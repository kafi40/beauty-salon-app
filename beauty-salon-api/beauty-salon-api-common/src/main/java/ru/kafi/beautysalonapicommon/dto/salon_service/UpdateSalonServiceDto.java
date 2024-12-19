package ru.kafi.beautysalonapicommon.dto.salon_service;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateSalonServiceDto {
    @Size(min = 1, max = 64, message = "The number of characters allowed is from 1 to 64")
    private String name;
    @Size(min = 20, max = 2000, message = "The number of characters allowed is from 1 to 2000")
    private String description;
    @Positive(message = "The price salary be greater than 0")
    private Double price;
    @Positive(message = "The duration salary be greater than 0")
    private Integer duration;
}
