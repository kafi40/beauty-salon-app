package ru.kafi.beautysalonbothandler.factory;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.kafi.beautysalonbotcommon.util.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyboardFactory {

    public static InlineKeyboardMarkup getMainMenuKeyBoard() {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        Map<Integer, Map<String, String>> menuMap = new HashMap<>() {{
            put(1, new HashMap<>() {
                {
                    put(Menu.PRICE_LIST.getText(), Menu.PRICE_LIST.name());
                    put(Menu.SUBSCRIBE.getText(), Menu.SUBSCRIBE.name());
                    put(Menu.GALLERY.getText(), Menu.GALLERY.name());
                }
            });
            put(2, new HashMap<>() {
                {
                    put(Menu.MAKE_APOINTMENT.getText(), Menu.MAKE_APOINTMENT.name());
                    put(Menu.MASTERS.getText(), Menu.MASTERS.name());
                    put(Menu.REGISTER.getText(), Menu.REGISTER.name());
                }
            });
            put(3, new HashMap<>() {
                {
                    put(Menu.ACCOUNT.getText(), Menu.ACCOUNT.name());
                    put(Menu.STOP.getText(), Menu.STOP.name());
                    put(Menu.INFO.getText(), Menu.INFO.name());
                }
            });
        }};

        fillRows(rows, menuMap);

        return new InlineKeyboardMarkup(rows);

    }

    public static InlineKeyboardMarkup getPersonalAccountKeyBoard() {
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        Map<Integer, Map<String, String>> menuMap = new HashMap<>() {{
            put(1, new HashMap<>() {
                {
                    put(Menu.ACCOUNT_INFO.getText(), Menu.ACCOUNT_INFO.name());
                    put(Menu.UPDATE_ACCOUNT.getText(), Menu.UPDATE_ACCOUNT.name());
                }
            });
            put(2, new HashMap<>() {
                {
                    put(Menu.BACK.getText(), Menu.BACK.name());
                    put(Menu.APOINTMENTS.getText(), Menu.APOINTMENTS.name());
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
