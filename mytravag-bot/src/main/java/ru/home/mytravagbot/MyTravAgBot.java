package ru.home.mytravagbot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.mytravagbot.botapi.TelegramPTRN;


public class MyTravAgBot extends TelegramWebhookBot {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    private TelegramPTRN telegramPTRN;


    public MyTravAgBot(DefaultBotOptions botOptions, TelegramPTRN telegramPTRN) {
        super(botOptions);
        this.telegramPTRN = telegramPTRN;
    }


    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        final BotApiMethod<?> replyMessageToUser = telegramPTRN.handleUpdate(update);

        return replyMessageToUser;
    }

    public void setWebHookPath(String webHookPath) {
        this.webHookPath = webHookPath;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

}
