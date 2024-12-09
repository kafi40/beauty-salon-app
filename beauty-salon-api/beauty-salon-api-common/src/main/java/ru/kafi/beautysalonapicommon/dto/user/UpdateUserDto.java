package ru.kafi.beautysalonapicommon.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.kafi.beautysalonapicommon.enums.Gender;

@Data
public class UpdateUserDto {
    @Size(min = 1, max = 32)
    private String firstName;
    @Size(min = 1, max = 32)
    private String lastName;
    @Size(min = 1, max = 32)
    private String middleName;
    @Email
    private String email;
    private Gender gender;
    private String birthday;
}
