package com.example.m10.domain.project.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChangeProjectNameRequest(
        @NotBlank
        String name
) {
}
