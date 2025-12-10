package ru.vaganov.bug2code_core.ai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vaganov.bug2code_core.ai.prompt.PromptProvider;

@Component
@RequiredArgsConstructor
@Slf4j
public class CodeGenerationService {
    private final AIClient aiClient;
    private final PromptProvider promptProvider;

    public String generateCode(String userTask) {
        log.info("Парсинг исходного промпта и примеров кода");
        var prompt = promptProvider.generatePrompt(userTask);
        log.info("Промпт сформирован");
        var result = aiClient.call(prompt);
        return result;
    }
}
