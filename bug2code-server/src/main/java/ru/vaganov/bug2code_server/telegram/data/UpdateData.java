package ru.vaganov.bug2code_server.telegram.data;

public record UpdateData(Long chatId, String telegramUsername, String messageText) {
}
