package com.example.m10.domain.node.presentation.dto.request;

import com.example.m10.domain.node.domain.enums.ai.ChatNode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LLMNodeRequestDto(
        @NotNull
        ChatNode chatNode,

        @NotBlank
        String prompt
) {
}
