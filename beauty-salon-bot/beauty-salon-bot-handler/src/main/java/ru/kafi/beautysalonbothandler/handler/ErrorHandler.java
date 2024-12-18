package ru.kafi.beautysalonbothandler.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.kafi.beautysalonbotcommon.cache.UserCache;
import ru.kafi.beautysalonbotcommon.dto.StateDto;
import ru.kafi.beautysalonbothandler.sender.CustomSender;


@Controller
@RequiredArgsConstructor
public class ErrorHandler {
    private final UserCache userCache;
    private final CustomSender sender;


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

    public BotApiMethod<?> handleGaleryAccessError(StateDto data) {
        return sender.sendMessage("Произошла ошибка при обработке вашего запроса, пожалуйста повторите " +
                "позже", data.getChatId());
    }

    public BotApiMethod<?> handleBadRequest(StateDto data) {
        return sender.sendMessage("Произошла ошибка при обработке данных, убедитесь что данные корректны",
                data.getChatId());
    }
}
