package ru.kafi.beautysalonbothandler.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonbotcommon.cache.UserCache;
import ru.kafi.beautysalonbotcommon.dto.StateDto;
import ru.kafi.beautysalonbotcommon.util.UserState;
import ru.kafi.beautysalonbothandler.client.UserTgClient;
import ru.kafi.beautysalonbothandler.factory.KeyboardFactory;


@Controller
@RequiredArgsConstructor
public class ResponseHandler {
    private final UserTgClient client;

    private final UserCache userCache;
    private final ErrorHandler errorHandler;

    public BotApiMethod<?> handle(StateDto data) {
        long chatId = data.getChatId();
        String text = data.getMessageText();

        if (data.getState() == UserState.INACTIVE) {
            if (text.equals("/start")) {
                return replyToMainMenu(data);
            }
            return null;
        }

        if (text.equals("/stop")) {

            SendMessage message = new SendMessage();
            message.setChatId(data.getChatId());
            message.setText("Хорошо, бот больше не будет отправлять вам сообщения пока вы вновь его не активируете");
            data.setState(UserState.INACTIVE);
            userCache.addNewState(chatId, data);

            return message;

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

    private BotApiMethod<?> replyToEmail(StateDto data) {
        NewUserDto userDto = userCache.getNewUser(data.getChatId());
        userDto.setEmail(data.getMessageText());
        SendMessage message = new SendMessage();
        message.setChatId(data.getChatId());

        ResponseEntity<?> response = client.post("api/users", userDto);

        if (response.getStatusCode() != HttpStatusCode.valueOf(201)) {
            return errorHandler.handle(data, response);
        }

        message.setText("Регистрация прошла успешно:\n" +
                "Ваше имя: " + userDto.getFirstName() + "\n" +
                "Почта: " + userDto.getEmail());

        data.setState(UserState.MAIN_MENU);
        userCache.addNewState(data.getChatId(), data);
        userCache.addRegistered(userDto.getTelegramId());

        return message;
    }

    private BotApiMethod<?> replyToMainMenu(StateDto data) {
        SendMessage message = new SendMessage();
        message.setChatId(data.getChatId());
        message.setText("Добро пожаловать в мини-приложение нашего салона красоты");
        message.setReplyMarkup(KeyboardFactory.getMainMenuKeyBoard());
        data.setState(UserState.MAIN_MENU);
        return message;
    }

    public BotApiMethod<?> replyToStart(StateDto data) {
        SendMessage message = new SendMessage();
        message.setChatId(data.getChatId());
        message.setText("Добро пожаловать");
        data.setState(UserState.MAIN_MENU);

        return message;
    }
}
