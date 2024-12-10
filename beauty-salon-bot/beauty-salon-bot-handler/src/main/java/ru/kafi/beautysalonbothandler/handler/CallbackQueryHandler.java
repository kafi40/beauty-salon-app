package ru.kafi.beautysalonbothandler.handler;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonbotcommon.cache.UserCache;
import ru.kafi.beautysalonbotcommon.dto.StateDto;
import ru.kafi.beautysalonbotcommon.util.Constants;
import ru.kafi.beautysalonbotcommon.util.UserState;
import ru.kafi.beautysalonbothandler.factory.KeyboardFactory;

@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CallbackQueryHandler {

    private final UserCache userCache;

    public BotApiMethod<?> processCallbackQuery(CallbackQuery callbackQuery, StateDto state) {

        switch (state.getData()) {
            case "register" -> {
                return processRegistration(callbackQuery, state);
            }
            case "info" -> {
                return processInfo(state);
            }
            case "personal-account" -> {
                return processPersonalAccount(state);
            }
            case "main-menu" -> {
                return processMainMenu(state);
            }
            case null, default -> {
                return null;
            }
        }

    }

    public BotApiMethod<?> processInfo(StateDto stateDto) {
        SendMessage message = new SendMessage();
        message.setChatId(stateDto.getChatId());
        message.setText(Constants.INFO_TEXT);
        return message;
    }

    public BotApiMethod<?> processRegistration(CallbackQuery callbackQuery, StateDto state) {
        if (userCache.isRegistered(callbackQuery.getFrom().getId())) {
            SendMessage message = new SendMessage();
            message.setChatId(state.getChatId());
            message.setText("Вы уже зарегистрированы");

            return message;
        }

        NewUserDto newUserDto = new NewUserDto();
        User from = callbackQuery.getFrom();

        newUserDto.setFirstName(from.getFirstName());
        newUserDto.setLastName(from.getLastName());
        newUserDto.setTelegramId(from.getId());

        userCache.addNewUser(state.getChatId(), newUserDto);
        state.setState(UserState.AWAITING_EMAIL);
        userCache.addNewState(state.getChatId(), state);

        SendMessage message = new SendMessage();
        message.setChatId(state.getChatId());
        message.setText("Пожалуйста введите свой email");

        return message;
    }
    public BotApiMethod<?> processPersonalAccount(StateDto stateDto) {
        SendMessage message = new SendMessage();
        message.setChatId(stateDto.getChatId());
        message.setText("Личный кабинет");
        message.setReplyMarkup(KeyboardFactory.getPersonalAccountKeyBoard());
        stateDto.setState(UserState.PERSONAL_ACCOUNT);
        return message;
    }
    public BotApiMethod<?> processMainMenu(StateDto stateDto) {
        SendMessage message = new SendMessage();
        message.setChatId(stateDto.getChatId());
        message.setText("Добро пожаловать в мини-приложение нашего салона красоты");
        message.setReplyMarkup(KeyboardFactory.getMainMenuKeyBoard());
        stateDto.setState(UserState.MAIN_MENU);
        return message;
    }
}
