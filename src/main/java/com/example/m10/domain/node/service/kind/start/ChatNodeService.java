package com.example.m10.domain.node.service.kind.start;

import com.example.m10.domain.node.presentation.dto.response.NodeResponse;
import com.example.m10.domain.node.service.common.StartNodeCreator;
import com.example.m10.domain.node.service.common.define.CommonStartNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatNodeService implements CommonStartNode {
    private final StartNodeCreator startNode;

    @Override
    @Transactional
    public NodeResponse createNode(Long projectId){
        return startNode.createNode(projectId);
    }
}
