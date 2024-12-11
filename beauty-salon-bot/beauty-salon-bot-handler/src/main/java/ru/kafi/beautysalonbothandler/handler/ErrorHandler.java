package ru.kafi.beautysalonbothandler.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.kafi.beautysalonbotcommon.cache.UserCache;
import ru.kafi.beautysalonbotcommon.dto.StateDto;

@Controller
@RequiredArgsConstructor
public class ErrorHandler {
    private final UserCache userCache;


    public BotApiMethod<?> handle(StateDto data, ResponseEntity<?> response) {
        HttpStatus code = HttpStatus.valueOf(response.getStatusCode().value());
        switch (code) {
            case HttpStatus.BAD_REQUEST -> {
                return handleBadRequest(data);
            }
            default -> {
                return null;
            }
        }
    }

    public BotApiMethod<?> handleBadRequest(StateDto data) {
        SendMessage message = new SendMessage();
        message.setChatId(data.getChatId());
        message.setText("Произошла ошибка при обработке данных, убедитесь что данные корректны");

        return message;
    }
}
