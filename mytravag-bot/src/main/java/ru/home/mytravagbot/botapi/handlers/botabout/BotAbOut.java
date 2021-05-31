package ru.home.mytravagbot.botapi.handlers.botabout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.mytravagbot.botapi.BotState;
import ru.home.mytravagbot.botapi.InputMessageHandler;
import ru.home.mytravagbot.cache.UserDataCache;
import ru.home.mytravagbot.service.ReplyMessagesService;


@Slf4j
@Component
public class BotAbOut implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    public BotAbOut(UserDataCache userDataCache,
                    ReplyMessagesService messagesService) {
        this.userDataCache = userDataCache;
        this.messagesService = messagesService;
    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.BOT_ABOUT;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(chatId,"reply.askDestiny");
        userDataCache.setUsersCurrentBotState(userId,BotState.FILLING_PROFILE);

        return replyToUser;
    }


}
