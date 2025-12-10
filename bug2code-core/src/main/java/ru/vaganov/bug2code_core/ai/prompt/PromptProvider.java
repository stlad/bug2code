package ru.vaganov.bug2code_core.ai.prompt;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import ru.vaganov.bug2code_core.cache.SourceCodeExamplesProvider;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class PromptProvider {
    private final SourceCodeExamplesProvider sourceCodeExamplesProvider;


    public String generatePrompt(String userTask) {
        return getPromptPrefix() + "\n" +
                sourceCodeExamplesProvider.getCodeExamples() + "\n" +
                userTask;
    }

    private String getPromptPrefix() {
        try {
            return StreamUtils.copyToString(
                    new ClassPathResource("prompt/prompt.txt").getInputStream(),
                    StandardCharsets.UTF_8
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
