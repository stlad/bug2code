package ru.vaganov.bug2code_server.telegram.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@Component
public class MessageCommandMapper {
    /**
     * Получить команду пользователя на основе данных, полученных от пользователя
     * @param update
     * @return
     */
    public BotCommand extractCommand(Update update) {
        //todo пока всегда делаем одну команду
        return BotCommand.GENERATE_CODE;
    }
}