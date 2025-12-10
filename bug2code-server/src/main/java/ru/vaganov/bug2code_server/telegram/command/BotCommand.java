package ru.vaganov.bug2code_server.telegram.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum BotCommand {
    GENERATE_CODE("generate_code"),
    GENERATE_PROMPT("generate_prompt"),
    NONE(null);

    private final String callbackPrefix;

    public static BotCommand fromString(String str) {
        var formattedCommand = str.split("/")[0];
        return Arrays.stream(BotCommand.values())
                .filter(cmd -> cmd.getCallbackPrefix() != null)
                .filter(cmd -> cmd.callbackPrefix.startsWith(formattedCommand)).findAny().orElse(NONE);
    }
}
