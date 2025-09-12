package com.example.m10.domain.project.presentation.dto.response;

import com.example.m10.domain.project.domain.Project;

public record ProjectResponse(
        Long id,
        String name,
        boolean active
) {
    public static ProjectResponse from(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.isActive()
        );
    }
}