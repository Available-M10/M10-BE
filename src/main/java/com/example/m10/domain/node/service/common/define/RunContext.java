package com.example.m10.domain.node.service.common.define;

import com.example.m10.domain.node.domain.entity.common.Node;

import java.util.Map;

public record RunContext(
        Long projectId,
        Node node,
        Map<String, Object> attrs
) {
}
