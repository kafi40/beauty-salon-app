package ru.kafi.beautysalonapicommon.dto.position;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePositionDto {
    @Size(min = 1, max = 50, message = "The number of characters allowed is from 1 to 50")
    private String name;
    @Positive(message = "The min salary be greater than 0")
    private Double minSalary;
    @Positive(message = "The max salary be greater than 0")
    private Double maxSalary;
}
