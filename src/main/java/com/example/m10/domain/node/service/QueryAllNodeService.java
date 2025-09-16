package com.example.m10.domain.node.service;

import com.example.m10.domain.node.domain.repository.NodeRepository;
import com.example.m10.domain.node.presentation.dto.response.QueryAllNodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QueryAllNodeService {

    private final NodeRepository nodeRepository;

    @Transactional(readOnly = true)
    public List<QueryAllNodeResponse> getAllNode(Long projectId){
        return nodeRepository.findAllByProjectId(projectId).stream()
                .map(QueryAllNodeResponse::from)
                .toList();
    }
}
