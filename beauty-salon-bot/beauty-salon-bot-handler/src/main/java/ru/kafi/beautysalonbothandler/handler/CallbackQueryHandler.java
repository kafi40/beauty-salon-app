package ru.kafi.beautysalonbothandler.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.kafi.beautysalonapicommon.dto.user.NewUserDto;
import ru.kafi.beautysalonbotcommon.cache.UserCache;
import ru.kafi.beautysalonbotcommon.dto.StateDto;
import ru.kafi.beautysalonbotcommon.util.Constants;
import ru.kafi.beautysalonbotcommon.util.UserState;
import ru.kafi.beautysalonbothandler.factory.KeyboardFactory;
import ru.kafi.beautysalonbothandler.sender.CustomSender;

@RequiredArgsConstructor
@Component
public class CallbackQueryHandler {

    private final UserCache userCache;
    private final CustomSender sender;
    private final ErrorHandler errorHandler;
    @Value("${photo-location.path}")
    private String galeryPath;

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
            case "gallery" -> {
                return errorHandler.handleGaleryAccessError(state);
            }
            case null, default -> {
                return null;
            }
        }

    }

    public SendMediaGroup processGallery(StateDto stateDto) {
        return sender.sendMessage("Вот фотографии из нашего салона", stateDto.getChatId(),
                galeryPath);
    }

    private BotApiMethod<?> processInfo(StateDto stateDto) {
        return sender.sendMessage(Constants.INFO_TEXT, stateDto.getChatId());
    }

    private BotApiMethod<?> processRegistration(CallbackQuery callbackQuery, StateDto state) {
        if (userCache.isRegistered(callbackQuery.getFrom().getId())) {
            return sender.sendMessage("Вы уже зарегистрированы", state.getChatId());
        }

        NewUserDto newUserDto = new NewUserDto();
        User from = callbackQuery.getFrom();

        newUserDto.setFirstName(from.getFirstName());
        newUserDto.setLastName(from.getLastName());
        newUserDto.setTelegramId(from.getId());

        userCache.addNewUser(state.getChatId(), newUserDto);
        state.setState(UserState.AWAITING_EMAIL);
        userCache.addNewState(state.getChatId(), state);

        return sender.sendMessage("Пожалуйста введите свой email", state.getChatId());
    }

    private BotApiMethod<?> processPersonalAccount(StateDto stateDto) {
        stateDto.setState(UserState.PERSONAL_ACCOUNT);
        return sender.sendMessage("Личный кабинет", stateDto.getChatId(),
                KeyboardFactory.getPersonalAccountKeyBoard());
    }

    private BotApiMethod<?> processMainMenu(StateDto stateDto) {
        stateDto.setState(UserState.MAIN_MENU);
        return sender.sendMessage("Добро пожаловать в мини-приложение нашего салона красоты",
                stateDto.getChatId(),
                KeyboardFactory.getMainMenuKeyBoard());
    }
}
