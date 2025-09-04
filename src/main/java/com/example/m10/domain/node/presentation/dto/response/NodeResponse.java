package com.example.m10.domain.node.presentation.dto.response;

import com.example.m10.domain.node.domain.Node;

public record NodeResponse(
        Long id,
        String name
) {
    public static NodeResponse from(Node node) {
        return new NodeResponse(
                node.getId(),
                node.getName()
        );
    }
}
