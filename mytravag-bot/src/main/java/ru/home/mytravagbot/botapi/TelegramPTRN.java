package ru.home.mytravagbot.botapi;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.home.mytravagbot.cache.UserDataCache;

@Component
@Slf4j
public class TelegramPTRN {
    private BotStateContext botStateContext;
    private UserDataCache userDataCache;

    public TelegramPTRN(BotStateContext botStateContext, UserDataCache userDataCache) {
        this.botStateContext = botStateContext;
        this.userDataCache = userDataCache;
    }

    public SendMessage handleUpdate(Update update) {
        SendMessage replyMessage = null;

        final Logger log = org.slf4j.LoggerFactory.getLogger(TelegramPTRN.class);

        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            log.info("New message from User:{}, chatId: {},  with text: {}",
                    message.getFrom().getUserName(), message.getChatId(), message.getText());
            replyMessage = handleInputMessage(message);
        }

        return replyMessage;
    }

    private SendMessage handleInputMessage(Message message) {
        String inputMsg = message.getText();
        int userId = message.getFrom().getId();
        BotState botState;
        SendMessage replyMessage;

        switch (inputMsg) {
            case "/start":
                botState = BotState.BOT_ABOUT;
                break;
            case "Вы получите информацию после заполнения анкеты.":
                botState = BotState.FILLING_PROFILE;
                break;
            case "Помощь":
                botState = BotState.SHOW_HELP_MENU;
                break;
            default:
                botState = userDataCache.getUsersCurrentBotState(userId);
                break;
        }

        userDataCache.setUsersCurrentBotState(userId, botState);

        replyMessage = botStateContext.processInputMessage(botState, message);

        return replyMessage;
    }

}
