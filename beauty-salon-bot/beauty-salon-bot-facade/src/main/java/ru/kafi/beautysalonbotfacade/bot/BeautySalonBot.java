package ru.kafi.beautysalonbotfacade.bot;

import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import ru.kafi.beautysalonbotfacade.facade.TelegramFacade;

@Getter
@Setter
public class BeautySalonBot extends SpringWebhookBot {

    private String botPath;
    private String botUserName;
    private String botToken;
    private TelegramFacade telegramFacade;


    public BeautySalonBot(final SetWebhook setWebhook, final String token, final TelegramFacade telegramFacade) {
        super(setWebhook, token);
        this.telegramFacade = telegramFacade;
    }


    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        return telegramFacade.handleUpdate(update);
    }

    @Override
    public String getBotPath() {
        return this.botPath;
    }

    @Override
    public String getBotUsername() {
        return this.botUserName;
    }
}
