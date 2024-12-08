package ru.kafi.beautysalonbothandler.factory;

import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ButtonFactory {

    public static InlineKeyboardMarkup getMainMenuKeyBoard() {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button;

        button = new InlineKeyboardButton();
        button.setText("Прайс-лист");
        button.setCallbackData("price-list");
        row.add(button);

        button = new InlineKeyboardButton();
        button.setText("Подписаться");
        button.setCallbackData("subscribe");
        row.add(button);

        button = new InlineKeyboardButton();
        button.setText("Галерея");
        button.setCallbackData("galery");
        row.add(button);

        rows.add(row);
        row = new ArrayList<>();

        button = new InlineKeyboardButton();
        button.setText("Записаться");
        button.setCallbackData("appointment");
        row.add(button);

        button = new InlineKeyboardButton();
        button.setText("Вывести мастеров");
        button.setCallbackData("masters");
        row.add(button);

        button = new InlineKeyboardButton();
        button.setText("Зарегистрировать аккаунт");
        button.setCallbackData("register");
        row.add(button);

        rows.add(row);
        row = new ArrayList<>();

        button = new InlineKeyboardButton();
        button.setText("Завершить сеанс");
        button.setCallbackData("stop");
        row.add(button);

        button = new InlineKeyboardButton();
        button.setText("Контакты\\связаться");
        button.setCallbackData("info");
        row.add(button);

        rows.add(row);

        return new InlineKeyboardMarkup(rows);

    }
}
