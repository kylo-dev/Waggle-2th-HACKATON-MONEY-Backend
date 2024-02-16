package com.waggle.moneyti.global.gpt.service;

import com.waggle.moneyti.global.config.ChatGPTConfig;
import com.waggle.moneyti.global.gpt.dto.ChatRequest;
import com.waggle.moneyti.global.gpt.dto.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PromptService {

    private final ChatGPTConfig chatGPTConfig;

    @Value("${chatgpt.model}")
    private String model;

    @Value("${chatgpt.url}")
    private String url;

    public String prompt(String prompt) {
        // 토큰 정보가 포함된 Header 가져오기
        HttpHeaders headers = chatGPTConfig.httpHeaders();

        // Create request
        ChatRequest chatRequest = new ChatRequest(model, prompt);

        // 통신을 위한 RestTemplate 구성하기
        HttpEntity<ChatRequest> requestEntity = new HttpEntity<>(chatRequest, headers);

        RestTemplate restTemplate = new RestTemplate();
        ChatResponse response = restTemplate.postForObject(url, requestEntity, ChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            throw new RuntimeException();
        }

        return response.getChoices().get(0).getChatMessage().getContent();
    }
}
