package ru.kafi.beautysalonapicommon.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.kafi.beautysalonapicommon.enums.Gender;

@Data
public abstract class NewUserDto {
    @NotBlank(message = "The firstname must be specified")
    @Size(min = 1, max = 32, message = "The number of characters allowed is from 1 to 32")
    protected String firstName;
    @Size(min = 1, max = 32, message = "The number of characters allowed is from 1 to 32")
    protected String lastName;
    @Size(min = 1, max = 32, message = "The number of characters allowed is from 1 to 32")
    protected String middleName;
    @Email
    protected String email;
    protected Gender gender;
    protected String birthday;
    @Positive(message = "The telegram ID be greater than 0")
    protected Long telegramId;
}
