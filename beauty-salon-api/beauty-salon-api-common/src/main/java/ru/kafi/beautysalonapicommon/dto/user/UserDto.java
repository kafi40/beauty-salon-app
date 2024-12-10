package ru.kafi.beautysalonapicommon.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public abstract class UserDto {
    @Positive
    private Long id;
    @Size(min = 1, max = 32)
    private String firstName;
    @Size(min = 1, max = 32)
    private String lastName;
    @Size(min = 1, max = 32)
    private String middleName;
    @Email
    private String email;
    private String gender;
    private String birthday;
    @Positive
    private Long telegramId;
}
