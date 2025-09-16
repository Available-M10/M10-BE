package com.example.m10.domain.node.presentation.dto.request;

import jakarta.validation.constraints.NotNull;

public record ChatRequestDto(
        @NotNull(message = "메세지를 입력해주세요")
        String message
) {
}
