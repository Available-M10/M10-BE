package com.example.m10.domain.node.service;

import com.example.m10.domain.node.domain.entity.common.Edge;
import com.example.m10.domain.node.domain.entity.common.Port;
import com.example.m10.domain.node.domain.repository.common.EdgeRepository;
import com.example.m10.domain.node.domain.repository.common.PortRepository;
import com.example.m10.domain.node.exception.EdgeAlreadyExistsException;
import com.example.m10.domain.node.exception.NodeConnectionException;
import com.example.m10.domain.project.domain.Project;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NextNode {
    private final PortRepository portRepository;
    private final EdgeRepository edgeRepository;

    @Transactional
    public void linkEdge(String previousPortId, Port currentPort, Project project){
        Port prevOutPort  = portRepository.findByOutPortIdAndNode_Project(UUID.fromString(previousPortId), project)
                .orElseThrow(NodeConnectionException::new);



        boolean exists = prevOutPort.getOutgoingEdges().stream()
                .anyMatch(e -> e.getToPort().equals(currentPort)); //모르겠노 펑
        if (exists) {
            throw new EdgeAlreadyExistsException();
        }


        Edge edge = Edge.builder()
                .project(project)
                .fromPort(prevOutPort)
                .toPort(currentPort)
                .build();

        prevOutPort.getOutgoingEdges().add(edge);
        currentPort.getIncomingEdges().add(edge);

        edgeRepository.save(edge);
    }
}
