package ru.kafi.beautysalonbothandler.handler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.kafi.beautysalonbothandler.dto.StateDto;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackQueryHandler {

    public BotApiMethod<?> processCallbackQuery(StateDto state) {
        return null;
    }
}
