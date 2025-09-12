package com.example.m10.domain.node.presentation.dto.response;

import com.example.m10.domain.node.domain.entity.common.Node;

public record NodeResponse(
        Long nodeId,
        String name,
        String type,
        String configJson,
        String myPortId
) {
    public static NodeResponse form(Node node, String myPortId){
        return new NodeResponse(
                node.getId(),
                node.getName().toString(),
                node.getType().toString(),
                node.getConfigJson(),
                myPortId
        );
    }

    public static NodeResponse form(Node node){
        return new NodeResponse(
                node.getId(),
                node.getName().toString(),
                node.getType().toString(),
                node.getConfigJson(),
                "LAST NODE"
        );
    }
}
