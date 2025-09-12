package com.example.m10.domain.node.service.common.define;

import com.example.m10.domain.node.presentation.dto.response.NodeResponse;

public interface CommonNode {
    NodeResponse createNode(Long projectId, String previousPortId, String json);
}
