package ru.kafi.beautysalonbotfacade.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.kafi.beautysalonbotfacade.bot.BeautySalonBot;
import ru.kafi.beautysalonbotfacade.facade.TelegramFacade;


@Configuration
@RequiredArgsConstructor
@Slf4j
@ComponentScan(basePackages = "ru.kafi.beautysalonbothandler")
public class SpringConfig {
    private final TelegramConfig telegramConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder()
                .url(telegramConfig.getBotPath())
                .build();
    }

    @Bean
    public BeautySalonBot springWebhookBot(SetWebhook setWebhook, TelegramFacade telegramFacade) throws TelegramApiException {
        BeautySalonBot beautySalonBot = new BeautySalonBot(setWebhook, telegramConfig.getBotToken(), telegramFacade);
        beautySalonBot.setBotPath(telegramConfig.getBotPath());
        beautySalonBot.setBotUserName(telegramConfig.getBotUserName());
        beautySalonBot.setBotToken(telegramConfig.getBotToken());

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        log.info("setting webhook bot BeautySalon {}", beautySalonBot.getSetWebhook());
        telegramBotsApi.registerBot(beautySalonBot, beautySalonBot.getSetWebhook());
        return beautySalonBot;
    }
}
