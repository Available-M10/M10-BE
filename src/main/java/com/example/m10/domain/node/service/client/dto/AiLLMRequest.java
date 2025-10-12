package com.example.m10.domain.node.service.client.dto;

import lombok.Builder;

@Builder
public record AiLLMRequest(
        String llm,
        String prompt,
        String message,
        int top_k
) {}
