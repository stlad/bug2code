package ru.vaganov.bug2code_server.telegram;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.vaganov.bug2code_server.telegram.command.CommandExecutor;
import ru.vaganov.bug2code_server.telegram.command.MessageCommandMapper;
import ru.vaganov.bug2code_server.telegram.data.TelegramBotUtils;
import ru.vaganov.bug2code_server.telegram.reply.SendObject;
import ru.vaganov.bug2code_server.telegram.reply.TelegramMessageSender;

@Component
@RequiredArgsConstructor
public class Bug2CodeBot extends TelegramLongPollingBot implements TelegramMessageSender {

    private final Logger logger = LoggerFactory.getLogger(Bug2CodeBot.class);

    @Value("${telegram.name}")
    private String name;

    @Value("${telegram.token}")
    private String token;

    private final CommandExecutor commandExecutor;
    private final MessageCommandMapper messageCommandMapper;

    private void processUpdate(Update update) {
        var command = messageCommandMapper.extractCommand(update);
        var updateData = TelegramBotUtils.createUpdateData(update);
        commandExecutor.executeCommand(command, updateData, this);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.getMessage() != null) {
            logger.info("Message: {} from {}", update.getMessage().getText(), update.getMessage().getChatId());
        }
        if (update.getCallbackQuery() != null) {
            logger.info("CallbackQuery: {} from {}", update.getCallbackQuery().getData(), update.getCallbackQuery().getMessage().getChatId());
        }
        processUpdate(update);
    }

    @Override
    public void send(SendObject wrapper) {
        try {
            if (wrapper.sendMessage() != null) {
                this.execute(wrapper.sendMessage());
            }
        } catch (Throwable e) {
            logger.error("Ошибка при отправке сообщения в чат {}", wrapper.chatId(), e);
        }
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return this.token;
    }
}