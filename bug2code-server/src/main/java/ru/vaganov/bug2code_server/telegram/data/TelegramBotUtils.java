package ru.vaganov.bug2code_server.telegram.data;

import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBotUtils {

    public static UpdateData createUpdateData(Update update) {
        var chatId = extractChatId(update);
        var userName = extractTelegramUserName(update);
        String messageText = update.getMessage() == null ? null : update.getMessage().getText();

        return new UpdateData(chatId, userName, messageText);
    }

    private static Long extractChatId(Update update) {
        if (update.getMessage() != null) {
            return update.getMessage().getChatId();
        } else {
            return update.getCallbackQuery().getMessage().getChatId();
        }
    }

    private static String extractTelegramUserName(Update update) {
        if (update.getMessage() != null) {
            return update.getMessage().getFrom().getUserName();
        } else {
            return update.getCallbackQuery().getMessage().getFrom().getUserName();
        }
    }

}
