package ru.kafi.beautysalonapicommon.dto.position;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewPositionDto {
    @NotBlank(message = "The name must be specified")
    @Size(min = 1, max = 50, message = "The number of characters allowed is from 1 to 50")
    private String name;
    @NotNull(message = "The min salary cannot be null")
    @Positive(message = "The min salary be greater than 0")
    private Double minSalary;
    @NotNull(message = "The max salary cannot be null")
    @Positive(message = "The max salary be greater than 0")
    private Double maxSalary;
}
