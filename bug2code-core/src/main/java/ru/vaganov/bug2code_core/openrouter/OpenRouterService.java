package ru.vaganov.bug2code_core.openrouter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class OpenRouterService {

    @Value("${openrouter.api.key}")
    private String apiKey;
    @Value("${openrouter.api.url}")
    private String apiUrl;
    @Value("${openrouter.api.model}")
    private String modelName;

    public String callOpenRouter(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");
        headers.set("HTTP-Referer", "https://your-app.com");

        var objectMapper = new ObjectMapper();
        var requestDto = OpenRouterDto.builder()
                .model(modelName)
                .messages(
                        List.of(
                                new OpenRouterDto.OpenRouterDtoMessage("user", prompt)
                        )
                ).build();

        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(requestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                modelName,
                HttpMethod.POST,
                entity,
                String.class
        );

        return response.getBody();
    }
}