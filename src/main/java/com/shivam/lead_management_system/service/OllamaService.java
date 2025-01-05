package com.shivam.lead_management_system.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
@Service
public class OllamaService {

    @Value("${spring.ai.ollama.base-url}")
    private String ollamaBaseUrl;

    @Value("${spring.ai.ollama.chat.options.model}")
    private String ollamaModel;

    private final RestTemplate restTemplate;

    public OllamaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateAnalysis(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", ollamaModel);
        requestBody.put("prompt", prompt);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                ollamaBaseUrl + "/api/generate",
                HttpMethod.POST,
                request,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return parseStreamingResponse(response.getBody());
        } else {
            throw new RuntimeException("Failed to generate analysis from Ollama API");
        }
    }

    private String parseStreamingResponse(String responseBody) {
        StringBuilder result = new StringBuilder();
        String[] lines = responseBody.split("\n");
        ObjectMapper objectMapper = new ObjectMapper();
        for (String line : lines) {
            if (!line.isEmpty()) {
                try {
                    Map<String, Object> responseMap = objectMapper.readValue(line, Map.class);
                    if (responseMap.containsKey("response")) {
                        result.append(responseMap.get("response"));
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Error parsing NDJSON response", e);
                }
            }
        }
        return result.toString();
    }

}
