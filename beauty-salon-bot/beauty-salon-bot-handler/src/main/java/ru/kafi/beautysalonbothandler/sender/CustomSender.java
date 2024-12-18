package ru.kafi.beautysalonbothandler.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomSender {

    public BotApiMethod<?> sendMessage(String message, long chatId) {
        SendMessage msg = new SendMessage();
        msg.setText(message);
        msg.setChatId(chatId);
        return msg;
    }

    public BotApiMethod<?> sendMessage(String message, long chatId, ReplyKeyboard keyboard) {
        SendMessage msg = new SendMessage();
        msg.setText(message);
        msg.setChatId(chatId);
        msg.setReplyMarkup(keyboard);
        return msg;
    }
}
