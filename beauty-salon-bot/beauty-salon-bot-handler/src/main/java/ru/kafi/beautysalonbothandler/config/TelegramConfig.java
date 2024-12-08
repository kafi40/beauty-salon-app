package ru.kafi.beautysalonbothandler.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TelegramConfig {
    @Value("${telegram.webhook-path}")
    private String botPath;
    @Value("${telegram.bot-username}")
    private String botUserName;
    @Value("${telegram.bot-token}")
    private String botToken;
}
