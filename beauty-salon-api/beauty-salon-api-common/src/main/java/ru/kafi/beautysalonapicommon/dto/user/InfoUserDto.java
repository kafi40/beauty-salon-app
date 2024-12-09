package ru.kafi.beautysalonapicommon.dto.user;

import lombok.Data;

@Data
public class InfoUserDto {
    protected Long id;
    protected String firstName;
    protected String lastName;
    protected String middleName;
    protected String email;
    protected String gender;
    protected String birthday;
}
