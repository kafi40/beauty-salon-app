package ru.kafi.beautysalonbothandler.factory;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyboardFactory {

    public static InlineKeyboardMarkup getMainMenuKeyBoard() {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        Map<Integer, Map<String, String>> menuMap = new HashMap<>() {{
                put(1, new HashMap<>() {{
                        put("Прайс-лист", "price-list");
                        put("Подписаться", "subscribe");
                        put("Галерея", "galery");
                }
                });
                put(2, new HashMap<>() {{
                    put("Записаться", "appointment");
                    put("Вывести мастеров", "masters");
                    put("Зарегистрировать аккаунт", "register");
                }
                });
                put(3, new HashMap<>() {{
                    put("Завершить сеанс", "stop");
                    put("Контакты\\связаться", "info");
                }
                });
            }};

        fillRows(rows, menuMap);

        return new InlineKeyboardMarkup(rows);

    }


    private static void fillRows(List<List<InlineKeyboardButton>> rows, Map<Integer, Map<String, String>> menu) {
        for (int row : menu.keySet()) {
            List<InlineKeyboardButton> newRow = new ArrayList<>();
            for (Map.Entry<String, String> fillData : menu.get(row).entrySet()) {
                InlineKeyboardButton newButton = new InlineKeyboardButton();
                newButton.setText(fillData.getKey());
                newButton.setCallbackData(fillData.getValue());
                newRow.add(newButton);
            }
            rows.add(newRow);
        }
    }
}
