package ru.kafi.beautysalonbothandler.handler;

import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import ru.kafi.beautysalonbothandler.factory.ButtonFactory;
import ru.kafi.beautysalonbothandler.util.Constants;
import ru.kafi.beautysalonbothandler.util.UserState;

import java.util.Map;

public class ResponseHandler {
    private final SilentSender sender;
    private final Map<Long, UserState> states;

    public ResponseHandler(SilentSender sender, DBContext db) {
        this.sender = sender;
        states = db.getMap(Constants.CHAT_STATES);

    }

    public void replyToStart(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(Constants.WELCOME_TEXT);
        message.setReplyMarkup(ButtonFactory.getMainMenuKeyBoard());
        sender.execute(message);
        states.put(chatId, UserState.MAIN_MENU);
    }

    public void replyToButtons(long chatId, Message message) {
        switch (message.getText()) {
            case "Галерея" -> states.put(chatId, UserState.GALERY);
            case "Прайс-лист" -> states.put(chatId, UserState.PRICE_LIST);
            case "Подписаться" -> states.put(chatId, UserState.SUBSCRIBE);
            case "Записаться" -> states.put(chatId, UserState.APPOINTMENT);
            case "Зарегистрировать аккаунт" -> states.put(chatId, UserState.REGISTER);
            case "Вывести мастеров" -> states.put(chatId, UserState.MASTERS_LIST);
            case "Контакты\\связаться" -> states.put(chatId, UserState.INFO);
            case "Завершить сеанс", "/stop" -> stopChat(chatId);
            default -> replyToUnexpected(chatId);
        }

        switch (states.get(chatId)) {
            case GALERY -> replyToGalery(chatId, message);
            case APPOINTMENT -> replyToAppointment(chatId, message);
            case PRICE_LIST -> replyToPriceList(chatId, message);
            case AWAITING_CONFIRMATION -> replyToConfimation(chatId, message);
            case AWAITING_NAME -> replyToName(chatId, message);
            case MASTERS_LIST -> replyToMasters(chatId, message);
            case SUBSCRIBE -> replyToSubscribe(chatId, message);
            case MAIN_MENU -> replyToBack(chatId, message);
            case INFO -> replyToInfo(chatId, message);
            case REGISTER -> replyToRegister(chatId, message);
        }
    }

    private void replyToRegister(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Чтобы зарегистрироваться введи сначала ваше имя пользователя, затем в следующем сообщении" +
                "введите пароль");
        sender.execute(sendMessage);
    }

    private void replyToInfo(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Телефон - + 7999999\n" +
                "Наши соцсети:\n" +
                "Инстаграм - \n" +
                "VK - \n");
        sender.execute(sendMessage);
    }

    private void replyToUnexpected(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Такой команды не существует");
        sendMessage.setReplyMarkup(ButtonFactory.getMainMenuKeyBoard());
        sender.execute(sendMessage);
    }

    private void stopChat(long chatId) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Нажмите start чтобы начать пользоваться вновь");
        states.remove(chatId);
        message.setReplyMarkup(new ReplyKeyboardRemove(true));
        sender.execute(message);

    }

    private void replyToGalery(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Вот последние фото из нашего салона.\n Если хотите увидеть " +
                "больше переходите в наш инстаграмм - instagram.com");
        // Вывод сообщений
        sender.execute(sendMessage);
        sendMessage.setReplyMarkup(ButtonFactory.getMainMenuKeyBoard());
        states.put(chatId, UserState.MAIN_MENU);
    }

    private void replyToAppointment(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Хорошо давайте запишем вас на приём");

        sender.execute(sendMessage);
        //логика записи на приём
    }

    private void replyToPriceList(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Список услуг:\n стрижка - 500 руб.\n ...");

        sender.execute(sendMessage);
    }

    private void replyToConfimation(long chatId, Message message) {

    }

    private void replyToName(long chatId, Message message) {

    }

    private void replyToMasters(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Список доступных мастеров:\n Ирина\n ...");
        //получение мастеров из базы
        sender.execute(sendMessage);
    }

    private void replyToSubscribe(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Теперь вы будете получать уведомления о новых событиях");
        sender.execute(sendMessage);

    }

    public boolean userIsActive(Long chatId) {
        return states.containsKey(chatId);
    }

    private void replyToBack(long chatId, Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(ButtonFactory.getMainMenuKeyBoard());
        sender.execute(sendMessage);
    }
}
