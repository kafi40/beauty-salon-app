package ru.kafi.beautysalonbothandler.bot;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.bot.BaseAbilityBot;
import org.telegram.abilitybots.api.objects.*;
import org.telegram.abilitybots.api.util.AbilityUtils;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kafi.beautysalonbothandler.handler.ResponseHandler;
import ru.kafi.beautysalonbothandler.util.Constants;

import java.util.function.BiConsumer;

@Component
public class BeautySalonBot extends AbilityBot {
    private final ResponseHandler responseHandler;

    public BeautySalonBot(Environment env) {
        super(env.getProperty("bot.token"), "BeautySalonAppTestBot");
        this.responseHandler = new ResponseHandler(silent, db);
    }

    public Ability start() {
        return Ability.builder()
                .name("start")
                .info(Constants.START_DESCRIPTION)
                .locality(Locality.USER)
                .privacy(Privacy.PUBLIC)
                .action(messageContext -> responseHandler.replyToStart(messageContext.chatId()))
                .build();
    }

    @Override
    public long creatorId() {
        return 1L;
    }

    public Reply replyToButtons() {
        BiConsumer<BaseAbilityBot, Update> action = (baseAbilityBot, update) ->
                responseHandler.replyToButtons(AbilityUtils.getChatId(update), update.getMessage());
        return Reply.of(action, Flag.TEXT, update -> responseHandler.userIsActive(AbilityUtils.getChatId(update)));

    }
}
