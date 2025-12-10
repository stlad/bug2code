package ru.vaganov.bug2code_server.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vaganov.bug2code_core.ai.CodeGenerationService;
import ru.vaganov.bug2code_core.ai.prompt.PromptProvider;

@RestController
@Component
@RequiredArgsConstructor
@RequestMapping("/")
public class Controller {
    private final CodeGenerationService codeGenerationService;
    private final PromptProvider promptProvider;

    @GetMapping("generate-ai-code")
    public ResponseEntity<String> getResponse(@RequestParam String userTask) {
        var result = codeGenerationService.generateCode(userTask);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("get-prompt")
    public ResponseEntity<String> getPrompt(@RequestParam String userTask) {
        var result = promptProvider.generatePrompt(userTask);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
