package ru.kafi.beautysalonbothandler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class BeautySalonBotHandlerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BeautySalonBotHandlerApplication.class, args);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(ctx.getBean("beautySalonBot", AbilityBot.class));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
