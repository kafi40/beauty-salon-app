package ru.kafi.beautysalonapicommon.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NewUserDto {
    @NotBlank
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
    private Long telegramId;
}
