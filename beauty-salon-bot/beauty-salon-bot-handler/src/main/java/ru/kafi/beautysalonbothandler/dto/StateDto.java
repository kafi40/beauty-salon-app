package ru.kafi.beautysalonbothandler.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.kafi.beautysalonbothandler.util.UserState;

@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class StateDto {
    Long id;
    Long chatId;
    String messageText;
    String callback;
    UserState state;

}
