package com.example.m10.domain.node.service.common;

import com.example.m10.domain.node.domain.entity.common.Node;
import com.example.m10.domain.node.domain.entity.common.Port;
import com.example.m10.domain.node.domain.enums.node.NodeName;
import com.example.m10.domain.node.domain.enums.node.NodeType;
import com.example.m10.domain.node.domain.repository.NodeRepository;
import com.example.m10.domain.node.domain.repository.PortRepository;
import com.example.m10.domain.node.exception.ChatNodeAlreadyExistsException;
import com.example.m10.domain.node.presentation.dto.response.NodeResponse;
import com.example.m10.domain.project.domain.Project;
import com.example.m10.domain.project.facade.ProjectFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class CommonStartNode {
    private final NodeRepository nodeRepository;
    private final PortRepository portRepository;
    private final ProjectFacade projectFacade;

    @Transactional
    public NodeResponse createNode(Long projectId){
        Project project = projectFacade.findByProject(projectId);

        if(nodeRepository.existsByProjectAndName(project, NodeName.CHAT)){
            throw new ChatNodeAlreadyExistsException();
        }

        Node node = nodeRepository.save(Node.builder()
                .name(NodeName.CHAT)
                .type(NodeType.START)
                .project(project)
                .build());

        Port outPort = portRepository.save(Port.builder().node(node).build());
        node.getPorts().add(outPort);
        return NodeResponse.form(node, outPort.getOutPortId().toString());
    }
}
