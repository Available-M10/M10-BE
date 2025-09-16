package com.example.m10.domain.node.service;

import com.example.m10.domain.node.domain.repository.EdgeRepository;
import com.example.m10.domain.node.domain.repository.NodeRepository;
import com.example.m10.domain.node.exception.NodeDeletionNotAllowedException;
import com.example.m10.domain.node.exception.NodeNotFoundException;
import com.example.m10.domain.node.service.pipeline.TraversalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteNodeService {
    private final NodeRepository nodeRepository;
    private final EdgeRepository edgeRepository;
    private final TraversalService traversalService;

    @Transactional
    public void delete(Long nodeId){
        if(!nodeRepository.existsById(nodeId)){
            throw new NodeNotFoundException();
        }

        if(traversalService.hasConnections(nodeId)){
            throw new NodeDeletionNotAllowedException();
        }

        edgeRepository.deleteByToPort_Node_Id(nodeId);
        nodeRepository.deleteById(nodeId);
    }
}
