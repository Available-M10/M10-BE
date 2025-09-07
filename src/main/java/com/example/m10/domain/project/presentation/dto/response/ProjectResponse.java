package com.example.m10.domain.project.presentation.dto.response;

import com.example.m10.domain.node.presentation.dto.response.NodeResponse;
import com.example.m10.domain.project.domain.Project;

import java.util.List;

public record ProjectResponse(
        Long id,
        String name,
        boolean active,
        List<NodeResponse> nodes
) {
    public static ProjectResponse from(Project project) {
        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.isActive(),
                project.getNodes().stream()
                        .map(NodeResponse::from)
                        .toList()
        );
    }
}