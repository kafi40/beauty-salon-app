package ru.kafi.beautysalonbothandler.dto;

import lombok.*;
import ru.kafi.beautysalonbothandler.util.UserState;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateDto {
    private Long id;
    private Long chatId;
    private String messageText;
    private String callback;
    private UserState state;
}
