package com.example.m10.domain.node.service;

import com.example.m10.domain.node.domain.repository.NodeRepository;
import com.example.m10.domain.node.domain.repository.PortRepository;
import com.example.m10.domain.node.exception.NodeDeletionNotAllowedException;
import com.example.m10.domain.node.exception.NodeNotFoundException;
import com.example.m10.domain.node.service.pipeline.TraversalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteNodeService {
    //노드 아이디를 입력하여 삭제 진행
    //만약 해당 노드가 자신 앞노드와 연결이 되었다면(자신의 OutPortId가 다른 노드의 InPortId 라면) 삭제 불가
    private final NodeRepository nodeRepository;
    private final TraversalService traversalService;

    @Transactional
    public void delete(Long nodeId){
        if(!nodeRepository.existsById(nodeId)){
            throw new NodeNotFoundException();
        }

        if(!traversalService.existsConnectedNode(nodeId)){
            throw new NodeDeletionNotAllowedException();
        }

        nodeRepository.deleteById(nodeId);
    }
}
