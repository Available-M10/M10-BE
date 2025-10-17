package com.example.m10.domain.node.service.client;

import com.example.m10.domain.node.service.client.dto.AiDocumentRequest;
import com.example.m10.domain.node.service.client.dto.AiLLMRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AiClient {

    private final WebClient webClient;

    public void sendDocumentRequest(Long projectId, AiDocumentRequest request) {
        System.out.println(request);
        webClient.post()
                .uri("/node/{projectId}/document", projectId)
                .bodyValue(request)
                .retrieve()
                .toBodilessEntity()
                .block();
    }

    public String sendLlmRequest(Long projectId, AiLLMRequest request) {
        return webClient.post()
                .uri("/node/{projectId}/llm", projectId)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
