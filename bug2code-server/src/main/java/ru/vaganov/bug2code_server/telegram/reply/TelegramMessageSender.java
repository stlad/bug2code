package ru.vaganov.bug2code_server.telegram.reply;


public interface TelegramMessageSender {
    void send(SendObject wrapper);
}