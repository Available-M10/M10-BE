package com.example.m10.domain.file.presentation.dto.response;

public record FileUploadUrlResponse(
        String objectKey,
        String presignedUrl
) {
}
