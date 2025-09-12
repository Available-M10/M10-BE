package com.example.m10.domain.node.presentation.dto.response;

public record ChatResponseDto(
        String role,
        String message
) {
    public static ChatResponseDto from(String role, String message){
        return new ChatResponseDto(
                role,
                message
        );
    }
}
