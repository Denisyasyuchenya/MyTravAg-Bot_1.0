package ru.home.mytravagbot.appconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import ru.home.mytravagbot.MyTravAgBot;
import ru.home.mytravagbot.botapi.TelegramPTRN;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "telegrambot")

public class BotConfig {

    private String webHookPath;
    private String botUserName;
    private String botToken;

    //private DefaultBotOptions.ProxyType proxyType;
    //private String proxyHost;
    //private int proxyPort;

    @Bean
    public MyTravAgBot myTravAgBot(TelegramPTRN telegramPTRN) {
        DefaultBotOptions options = ApiContext
                .getInstance(DefaultBotOptions.class);

        MyTravAgBot myTravAgBot = new MyTravAgBot(options,telegramPTRN);
        myTravAgBot.setBotUserName(botUserName);
        myTravAgBot.setBotToken(botToken);
        myTravAgBot.setWebHookPath(webHookPath);

        return myTravAgBot;
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;

    }
}

