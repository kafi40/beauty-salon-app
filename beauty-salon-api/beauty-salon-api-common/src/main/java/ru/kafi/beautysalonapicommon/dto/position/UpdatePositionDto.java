package ru.kafi.beautysalonapicommon.dto.position;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdatePositionDto {
    @Size(min = 1, max = 50)
    private String name;
    @Positive
    private Double minSalary;
    @Positive
    private Double maxSalary;
}
