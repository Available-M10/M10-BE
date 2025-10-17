package com.example.m10.domain.node.service.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record AiLLMRequest(
        String llm,
        String prompt,
        String message,
        @JsonProperty("top_k")
        int topK
) {}
