package ru.kafi.beautysalonbotfacade.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kafi.beautysalonbotcommon.cache.UserCache;
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
    private final UserCache userCache;

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
        StateDto.StateDtoBuilder stateB = StateDto.builder();
        StateDto state = userCache.getState(message.getChatId());

        if (state == null) {
            if (!message.getText().equals("/start")) {
                return null;
            }
            stateB.chatId(message.getChatId());
            stateB.messageText(message.getText());
            stateB.state(UserState.MAIN_MENU);
            state = stateB.build();
            userCache.addNewState(message.getChatId(), state);
        } else {
            state.setMessageText(message.getText());
        }
        return responseHandler.handle(state);
    }

    public BotApiMethod<?> withCallbackQuery(CallbackQuery callbackQuery) {
        StateDto state = userCache.getState(callbackQuery.getMessage().getChatId());


        long chatId = callbackQuery.getMessage().getChatId();
        StateDto stateDto = new StateDto();
        stateDto.setChatId(chatId);
        stateDto.setData(callbackQuery.getData());

        return callbackQueryHandler.processCallbackQuery(callbackQuery, stateDto);
    }
}
