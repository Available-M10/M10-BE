package com.example.m10.domain.auth.dto.response;

import lombok.Builder;

@Builder
public record TokenResponseDto(
        String accessToken,
        String refreshToken
) {
}
