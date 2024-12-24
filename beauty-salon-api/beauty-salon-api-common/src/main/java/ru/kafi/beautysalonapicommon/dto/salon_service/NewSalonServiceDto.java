package ru.kafi.beautysalonapicommon.dto.salon_service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewSalonServiceDto {
    @NotBlank(message = "The name must be specified")
    @Size(min = 1, max = 64, message = "The number of characters allowed is from 1 to 64")
    private String name;
    @NotBlank(message = "The description must be specified")
    @Size(min = 20, max = 2000, message = "The number of characters allowed is from 1 to 2000")
    private String description;
    @NotNull
    @Min(value = 100, message = "The price be greater than 100")
    private Double price;
    @NotNull
    @Min(value = 30, message = "The duration be greater than 30 minutes")
    private Integer duration;
}
