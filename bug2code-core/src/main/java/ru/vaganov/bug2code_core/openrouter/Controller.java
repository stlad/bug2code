package ru.vaganov.bug2code_core.openrouter;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
@RequiredArgsConstructor
@RequestMapping("/")
public class Controller {
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;
    @Value("${spring.ai.openai.base-url}")
    private String apiUrl;
    @Value("${spring.ai.openai.chat.options.model}")
    private String modelName;

    private final OpenRouterService openRouterService;

    @GetMapping("openrouter")
    public ResponseEntity<String> getResponse(@RequestParam String prompt) {
        var result = openRouterService.callOpenRouter(prompt);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
