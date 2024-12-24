package ru.kafi.beautysalonbothandler.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonbotcommon.cache.UserCache;
import ru.kafi.beautysalonbotcommon.dto.StateDto;
import ru.kafi.beautysalonbotcommon.util.UserState;
import ru.kafi.beautysalonbothandler.client.Client;
import ru.kafi.beautysalonbothandler.factory.KeyboardFactory;
import ru.kafi.beautysalonbothandler.sender.CustomSender;


@Controller
@RequiredArgsConstructor
public class ResponseHandler {
    private final Client client;
    private final UserCache userCache;
    private final ErrorHandler errorHandler;
    private final CustomSender sender;

    public BotApiMethod<?> handle(final StateDto data) {
        long chatId = data.getChatId();
        String text = data.getMessageText();

        if (data.getState() == UserState.INACTIVE) {
            if (text.equals("/start")) {
                return replyToMainMenu(data);
            }
            return null;
        }

        if (text.equals("/stop")) {
            data.setState(UserState.INACTIVE);
            userCache.addNewState(chatId, data);
            return sender.sendMessage("Хорошо, бот больше не будет отправлять вам сообщения пока вы вновь его не активируете",
                    data.getChatId());

        }

        switch (data.getState()) {
            case MAIN_MENU -> {
                return replyToMainMenu(data);
            }
            case AWAITING_EMAIL -> {
                return replyToEmail(data);
            }
            case null, default -> {
                return null;
            }
        }
    }

    private BotApiMethod<?> replyToEmail(final StateDto data) {
        NewClientDto userDto = userCache.getNewUser(data.getChatId());
        userDto.setEmail(data.getMessageText());
        ResponseEntity<?> response = client.post("api/clients", userDto);

        if (response.getStatusCode() != HttpStatusCode.valueOf(201)) {
            return errorHandler.handle(data, response);
        }

        String messageText = "Регистрация прошла успешно:\n" +
                "Ваше имя: " + userDto.getFirstName() + "\n" +
                "Почта: " + userDto.getEmail();

        data.setState(UserState.MAIN_MENU);
        userCache.addNewState(data.getChatId(), data);
        userCache.addRegistered(userDto.getTelegramId());

        return sender.sendMessage(messageText, data.getChatId());
    }

    private BotApiMethod<?> replyToMainMenu(final StateDto data) {
        data.setState(UserState.MAIN_MENU);
        return sender.sendMessage("Добро пожаловать в мини-приложение нашего салона красоты",
                data.getChatId(),
                KeyboardFactory.getMainMenuKeyBoard());
    }

    private BotApiMethod<?> replyToStart(final StateDto data) {
        data.setState(UserState.MAIN_MENU);
        return sender.sendMessage("Добро пожаловать", data.getChatId());
    }
}
