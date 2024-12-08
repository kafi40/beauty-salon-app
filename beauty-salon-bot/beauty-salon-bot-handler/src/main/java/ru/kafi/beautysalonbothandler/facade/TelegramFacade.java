package ru.kafi.beautysalonbothandler.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kafi.beautysalonbothandler.dto.StateDto;
import ru.kafi.beautysalonbothandler.handler.ResponseHandler;
import ru.kafi.beautysalonbothandler.util.UserState;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramFacade {

    private final ResponseHandler responseHandler;

    public BotApiMethod<?> handleUpdate(Update update) {
        System.out.println("testHandle");
        if (update.hasCallbackQuery()) {
            return withCallbackQuery(update.getCallbackQuery());
        } else {
            Message message = update.getMessage();
            if (message != null) {
                log.info("New message from User:{}, chatId: {},  with text: {}",
                        message.getFrom().getUserName(), message.getChatId(), message.getText());
                return withMessage(message);
            }
        }

        return null;
    }

    public BotApiMethod<?> withMessage(Message message) {
        StateDto state = new StateDto(message.getChatId());
        state.setMessageText(message.getText());
        state.setState(UserState.MAIN_MENU);

        return responseHandler.handle(state);
    }

    public BotApiMethod<?> withCallbackQuery(CallbackQuery callbackQuery) {
        long chatId = callbackQuery.getMessage().getChatId();

        return null;
    }
}
