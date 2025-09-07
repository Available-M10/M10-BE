package com.example.m10.domain.project.presentation.dto.response;

import com.example.m10.domain.project.domain.Project;

public record QueryAllProjectResponse(
        Long id,
        String name,
        boolean active
) {
    public static QueryAllProjectResponse from(Project project) {
        return new QueryAllProjectResponse(
                project.getId(),
                project.getName(),
                project.isActive()
        );
    }
}
