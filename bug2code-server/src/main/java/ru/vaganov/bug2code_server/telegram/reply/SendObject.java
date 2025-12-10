package ru.vaganov.bug2code_server.telegram.reply;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public record SendObject(Long chatId, SendMessage sendMessage) {

    public static SendObject message(Long chatId, String message) {
        var sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text(message)
                .build();
        return new SendObject(chatId, sendMessage);
    }

    public static SendObject error(Long chatId) {
        var sendMessage = SendMessage.builder()
                .chatId(chatId)
                .text("Случилась ошибка")
                .build();
        return new SendObject(chatId, sendMessage);
    }
}
