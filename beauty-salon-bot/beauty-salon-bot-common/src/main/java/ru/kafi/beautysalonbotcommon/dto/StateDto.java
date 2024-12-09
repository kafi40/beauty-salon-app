package ru.kafi.beautysalonbotcommon.dto;

import ru.kafi.beautysalonbotcommon.util.UserState;
import lombok.*;

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
