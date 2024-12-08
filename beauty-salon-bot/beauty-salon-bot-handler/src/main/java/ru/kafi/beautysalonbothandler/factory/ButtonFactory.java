package ru.kafi.beautysalonbothandler.factory;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class ButtonFactory {

    public static ReplyKeyboard getMainMenuKeyBoard() {
        KeyboardRow row1 = new KeyboardRow();
        row1.add("Прайс-лист");
        row1.add("Подписаться");
        row1.add("Галерея");
        KeyboardRow row2 = new KeyboardRow();
        row2.add("Записаться");
        row2.add("Зарегистрировать аккаунт");
        row2.add("Вывести мастеров");
        KeyboardRow row3 = new KeyboardRow();
        row3.add("Завершить сеанс");
        row3.add("Контакты\\связаться");
        return new ReplyKeyboardMarkup(List.of(row1,row2,row3));

    }
}
