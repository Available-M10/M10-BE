package com.example.m10.domain.node.service.common.define;

import com.example.m10.domain.node.presentation.dto.response.NodeResponse;

//트리거를 생성할 수 있는 노드는 **무조건** 따로 API를 만들어야 함
public interface CommonStartNode {
    NodeResponse createNode(Long projectId);
}
