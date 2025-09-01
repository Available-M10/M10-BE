package com.example.m10.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDto(
        @NotBlank
        String accountId,
        @NotBlank
        String password
) {
}
