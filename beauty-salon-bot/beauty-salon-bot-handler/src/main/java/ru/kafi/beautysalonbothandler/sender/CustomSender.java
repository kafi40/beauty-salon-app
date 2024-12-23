package ru.kafi.beautysalonbothandler.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ru.kafi.beautysalonapicommon.dto.salon_service.InfoSalonServiceDto;
import ru.kafi.beautysalonapicommon.dto.user.employee.InfoEmployeeDto;

import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class CustomSender {

    public BotApiMethod<?> sendMessage(final String message, final long chatId) {
        SendMessage msg = new SendMessage();
        msg.setText(message);
        msg.setChatId(chatId);
        return msg;
    }

    public BotApiMethod<?> sendMessage(final String message, final long chatId, final ReplyKeyboard keyboard) {
        SendMessage msg = new SendMessage();
        msg.setText(message);
        msg.setChatId(chatId);
        msg.setReplyMarkup(keyboard);
        return msg;
    }

    public BotApiMethod<?> sendMessage(final List<InfoEmployeeDto> masters, long chatId) {
        StringBuilder builder = new StringBuilder();
        SendMessage msg = new SendMessage();
        builder.append("Список доступных мастеров: \n");
        for (InfoEmployeeDto master : masters) {
            builder.append(master.getFirstName()).append(" ").append(master.getLastName()).append("\n");
        }


        msg.setText(builder.toString());
        msg.setChatId(chatId);
        return msg;
    }

    public BotApiMethod<?> sendMessage(final long chatId, final List<InfoSalonServiceDto> services) {
        StringBuilder builder = new StringBuilder();
        SendMessage msg = new SendMessage();
        builder.append("Прайс-лит оказываемых нашим салоном услуг: \n");
        for (int i = 0; i < services.size(); i++) {
            InfoSalonServiceDto service = services.get(i);
            builder.append(i).append(". ").append(service.getName()).append(": ")
                    .append(service.getPrice()).append(", длительность сессии: ")
                    .append(service.getDuration()).append(" м.\n");
        }
        msg.setText(builder.toString());
        msg.setChatId(chatId);
        return msg;
    }
}
