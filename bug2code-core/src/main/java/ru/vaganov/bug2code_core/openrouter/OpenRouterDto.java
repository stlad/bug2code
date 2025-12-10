package ru.vaganov.bug2code_core.openrouter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class OpenRouterDto {
    private String model;
    private List<OpenRouterDtoMessage> messages;


    @Getter
    @Setter
    @Builder
    static
    class OpenRouterDtoMessage {
        private String role;
        private String content;
    }
}

