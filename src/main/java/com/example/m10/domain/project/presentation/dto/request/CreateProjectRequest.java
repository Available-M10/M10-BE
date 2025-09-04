package com.example.m10.domain.project.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateProjectRequest(
        @NotBlank(message = "프로젝트 이름을 입력해주세요.")
        String name
) {
}
