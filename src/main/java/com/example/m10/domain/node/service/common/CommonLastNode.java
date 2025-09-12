package com.example.m10.domain.node.service.common;

import com.example.m10.domain.node.domain.entity.common.Node;
import com.example.m10.domain.node.domain.entity.common.Port;
import com.example.m10.domain.node.domain.enums.node.NodeName;
import com.example.m10.domain.node.domain.enums.node.NodeType;
import com.example.m10.domain.node.domain.repository.NodeRepository;
import com.example.m10.domain.node.domain.repository.PortRepository;
import com.example.m10.domain.node.presentation.dto.response.NodeResponse;
import com.example.m10.domain.node.service.NextNode;
import com.example.m10.domain.project.domain.Project;
import com.example.m10.domain.project.facade.ProjectFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CommonLastNode {
    private final NodeRepository nodeRepository;
    private final PortRepository portRepository;
    private final ProjectFacade projectFacade;
    private final NextNode nextNode;

    @Transactional
    public NodeResponse createNode(Long projectId, String previousPortId, String json){
        Project project = projectFacade.findByProject(projectId);

        Node node = nodeRepository.save(Node.builder()
                .name(NodeName.LLM)
                .type(NodeType.LAST)
                .configJson(json)
                .project(project)
                .build());

        Port inPort = portRepository.save(Port.builder()
                .inPortId(UUID.fromString(previousPortId))
                .node(node)
                .build());
        inPort.lastOutPortId();

        nextNode.linkEdge(previousPortId, inPort, project);
        return NodeResponse.form(node);
    }
}
