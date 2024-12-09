package ru.kafi.beautysalonbotfacade.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kafi.beautysalonbotcommon.dto.StateDto;
import ru.kafi.beautysalonbothandler.handler.CallbackQueryHandler;
import ru.kafi.beautysalonbothandler.handler.ResponseHandler;
import ru.kafi.beautysalonbotcommon.util.UserState;

@Component
@Slf4j
@RequiredArgsConstructor
public class TelegramFacade {

    private final ResponseHandler responseHandler;
    private final CallbackQueryHandler callbackQueryHandler;

    public BotApiMethod<?> handleUpdate(Update update) {
        if (update.hasCallbackQuery()) {
            log.info("new callback query from user - {}, with data - {}", update.getCallbackQuery().getFrom().getUserName(),
                    update.getCallbackQuery().getData());
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

        StateDto state = StateDto.builder().
                chatId(message.getChatId())
                .messageText(message.getText())
                .state(UserState.MAIN_MENU)
                .build();

        return responseHandler.handle(state);
    }

    public BotApiMethod<?> withCallbackQuery(CallbackQuery callbackQuery) {
        long chatId = callbackQuery.getMessage().getChatId();
        StateDto stateDto = new StateDto();
        stateDto.setChatId(chatId);

        return callbackQueryHandler.processCallbackQuery(stateDto);
    }
}
