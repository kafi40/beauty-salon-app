package ru.kafi.beautysalonbothandler.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    public SendMediaGroup sendMessage(String message, long chatId, String folderPath) {
        SendMediaGroup msg = new SendMediaGroup();
        msg.setChatId(chatId);
        List<InputMedia> photoList = new ArrayList<>();
        File folder = new java.io.File(folderPath);
        File[] imageList = folder.listFiles();

        if (imageList != null) {
            for (File file : imageList) {
                if (file.isFile()) {
                    InputMediaPhoto mediaPhoto = new InputMediaPhoto();
                    mediaPhoto.setMedia(file, file.getName());
                    photoList.add(mediaPhoto);
                }
            }
        }
        msg.setMedias(photoList);

        return msg;

    }
}
