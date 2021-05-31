package ru.home.mytravagbot.cache;

import ru.home.mytravagbot.botapi.BotState;
import ru.home.mytravagbot.botapi.handlers.fillingprofile.UserProfileData;


public interface DataCache {
    void setUsersCurrentBotState(int userId, BotState botState);

    BotState getUsersCurrentBotState(int userId);

    UserProfileData getUserProfileData(int userId);

    void saveUserProfileData(int userId, UserProfileData userProfileData);
}