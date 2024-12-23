package ru.kafi.beautysalonapicommon.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import ru.kafi.beautysalonapicommon.enums.Gender;

import java.time.LocalDate;

@Data
public abstract class UpdateUserDto {
    @Size(min = 1, max = 32, message = "The number of characters allowed is from 1 to 32")
    protected String firstName;
    @Size(min = 1, max = 32, message = "The number of characters allowed is from 1 to 32")
    protected String lastName;
    @Size(min = 1, max = 32, message = "The number of characters allowed is from 1 to 32")
    protected String middleName;
    @Email(message = "The email doesn't match the format")
    protected String email;
    protected Gender gender;
    @Past(message = "The birthday should be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected LocalDate birthday;
}
