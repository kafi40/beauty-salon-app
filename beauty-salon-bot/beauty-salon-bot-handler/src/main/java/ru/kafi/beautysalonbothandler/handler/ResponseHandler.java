package ru.kafi.beautysalonbothandler.handler;

import lombok.RequiredArgsConstructor;
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

    public BotApiMethod<?> handle(StateDto data) {
        long chatId = data.getChatId();
        String text = data.getMessageText();


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



        client.post("api/users", userDto);
        message.setText("Регистрация прошла успешно:\n" +
                "Ваше имя: "+ userDto.getFirstName() + "\n" +
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
