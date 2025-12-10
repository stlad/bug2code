package ru.vaganov.bug2code_server.telegram.command;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vaganov.bug2code_core.ai.CodeGenerationService;
import ru.vaganov.bug2code_server.telegram.data.UpdateData;
import ru.vaganov.bug2code_server.telegram.reply.SendObject;
import ru.vaganov.bug2code_server.telegram.reply.TelegramMessageSender;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandExecutor {
    private final CodeGenerationService codeGenerationService;

    public void executeCommand(BotCommand command, UpdateData updateData, TelegramMessageSender sender) {
        try {
            chooseExecution(command, updateData, sender);
        } catch (Exception exception) {
            log.error("chatId: {}, {}, ", updateData.chatId(), exception.getMessage());
            sender.send(SendObject.error(updateData.chatId()));
        }
    }

    private void chooseExecution(BotCommand command, UpdateData updateData, TelegramMessageSender sender) {
        switch (command) {
            case GENERATE_CODE -> {
                log.info("Выполняется команда: {}", BotCommand.GENERATE_CODE);
                var result = SendObject.message(
                        updateData.chatId(),
                        codeGenerationService.generateCode(updateData.messageText())
                );
                sender.send(result);
            }
            case GENERATE_PROMPT -> {
                log.info("Выполняется команда: {}", BotCommand.GENERATE_PROMPT);
                var result = SendObject.message(
                        updateData.chatId(),
                        codeGenerationService.getPromptByTask(updateData.messageText())
                );
                sender.send(result);
            }
            default -> {
                throw new RuntimeException("Unknown command");
            }
        }
    }
}