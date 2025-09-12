package com.example.m10.domain.node.presentation.dto.response;

import com.example.m10.domain.node.domain.entity.common.Node;
import com.example.m10.domain.node.domain.entity.common.Port;

import java.util.List;

public record QueryAllNodeResponse(
        String name,
        String type,
        List<Port> port
) {
    public static QueryAllNodeResponse from(Node node){
        return new QueryAllNodeResponse(
                node.getName().toString(),
                node.getType().toString(),
                node.getPorts()
        );
    }
}


