package ru.kafi.beautysalonbothandler.handler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.kafi.beautysalonbothandler.dto.StateDto;
import ru.kafi.beautysalonbothandler.factory.KeyboardFactory;
import ru.kafi.beautysalonbothandler.util.UserState;

@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ResponseHandler {

    public BotApiMethod<?> handle(StateDto data) {
        long chatId = data.getChatId();
        String text = data.getMessageText();


        switch(data.getState()) {
            case MAIN_MENU -> {
                return replyToMainMenu(data);
            }
            default -> {
                return null;
            }
        }
    }

    private BotApiMethod<?> replyToMainMenu(StateDto data) {
        SendMessage message = new SendMessage();
        message.setChatId(data.getChatId());
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
