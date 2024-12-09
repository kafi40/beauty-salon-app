package ru.kafi.beautysalonapicommon.dto.position;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewPositionDto {
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    @Positive
    private Double minSalary;
    @NotNull
    @Positive
    private Double maxSalary;
}
