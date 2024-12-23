package ru.kafi.beautysalonbothandler.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.kafi.beautysalonapicommon.dto.salon_service.InfoSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.user.client.NewClientDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;
import ru.kafi.beautysalonbotcommon.cache.UserCache;
import ru.kafi.beautysalonbotcommon.dto.StateDto;
import ru.kafi.beautysalonbotcommon.util.Constants;
import ru.kafi.beautysalonbotcommon.util.Menu;
import ru.kafi.beautysalonbotcommon.util.UserState;
import ru.kafi.beautysalonbothandler.client.Client;
import ru.kafi.beautysalonbothandler.factory.KeyboardFactory;
import ru.kafi.beautysalonbothandler.sender.CustomSender;

import java.util.List;

@RequiredArgsConstructor
@Component
public class CallbackQueryHandler {

    private final UserCache userCache;
    private final CustomSender sender;
    private final Client<InfoEmployeeDto> userClient;
    private final Client<InfoSalonServiceDto> serviceClient;

    public BotApiMethod<?> processCallbackQuery(final CallbackQuery callbackQuery, final StateDto state) {

        switch (Menu.valueOf(state.getData())) {
            case Menu.REGISTER -> {
                return processRegistration(callbackQuery, state);
            }
            case Menu.INFO -> {
                return processInfo(state);
            }
            case Menu.ACCOUNT -> {
                return processPersonalAccount(state);
            }
            case Menu.BACK -> {
                return processMainMenu(state);
            }
            case Menu.MASTERS -> {
                return processMasters(state);
            }
            case Menu.PRICE_LIST -> {
                return processPriceList(state);
            }
            default -> {
                return null;
            }
        }

    }

    private BotApiMethod<?> processPriceList(final StateDto stateDto) {
        List<InfoSalonServiceDto> serviceList = serviceClient.getAll("/services");

        return sender.sendMessage(stateDto.getChatId(), serviceList);
    }

    private BotApiMethod<?> processMasters(final StateDto stateDto) {
        Page<InfoEmployeeDto> mastersPage = userClient.getPage("/api/admin/employees");
        List<InfoEmployeeDto> masters = mastersPage.getContent();

        return sender.sendMessage(masters, stateDto.getChatId());
    }

    private BotApiMethod<?> processInfo(final StateDto stateDto) {
        return sender.sendMessage(Constants.INFO_TEXT, stateDto.getChatId());
    }

    private BotApiMethod<?> processRegistration(final CallbackQuery callbackQuery, final StateDto state) {
        if (userCache.isRegistered(callbackQuery.getFrom().getId())) {
            return sender.sendMessage("Вы уже зарегистрированы", state.getChatId());
        }

        NewClientDto newUserDto = new NewClientDto();
        User from = callbackQuery.getFrom();

        newUserDto.setFirstName(from.getFirstName());
        newUserDto.setLastName(from.getLastName());
        newUserDto.setTelegramId(from.getId());

        userCache.addNewUser(state.getChatId(), newUserDto);
        state.setState(UserState.AWAITING_EMAIL);
        userCache.addNewState(state.getChatId(), state);

        return sender.sendMessage("Пожалуйста введите свой email", state.getChatId());
    }

    private BotApiMethod<?> processPersonalAccount(final StateDto stateDto) {
        stateDto.setState(UserState.PERSONAL_ACCOUNT);
        return sender.sendMessage("Личный кабинет", stateDto.getChatId(),
                KeyboardFactory.getPersonalAccountKeyBoard());
    }

    private BotApiMethod<?> processMainMenu(final StateDto stateDto) {
        stateDto.setState(UserState.MAIN_MENU);
        return sender.sendMessage("Добро пожаловать в мини-приложение нашего салона красоты",
                stateDto.getChatId(),
                KeyboardFactory.getMainMenuKeyBoard());
    }
}
