package ru.kafi.beautysalonapicommon.dto.user;

import lombok.Data;
import ru.kafi.beautysalonapicommon.enums.Gender;

@Data
public abstract class InfoUserDto {
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String middleName;
    protected String email;
    protected Gender gender;
    protected String birthday;
}
