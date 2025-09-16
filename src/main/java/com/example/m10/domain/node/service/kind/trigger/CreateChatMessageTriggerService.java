package com.example.m10.domain.node.service.kind.trigger;

import com.example.m10.domain.node.domain.entity.common.Node;
import com.example.m10.domain.node.domain.entity.common.Port;
import com.example.m10.domain.node.domain.entity.trigger.ChatMessage;
import com.example.m10.domain.node.domain.enums.chat.Role;
import com.example.m10.domain.node.domain.enums.node.NodeName;
import com.example.m10.domain.node.domain.enums.node.TriggerType;
import com.example.m10.domain.node.domain.repository.ChatMessageRepository;
import com.example.m10.domain.node.domain.repository.NodeRepository;
import com.example.m10.domain.node.domain.repository.PortRepository;
import com.example.m10.domain.node.exception.ChatNodeNotFoundException;
import com.example.m10.domain.node.exception.InvalidStartPortException;
import com.example.m10.domain.node.presentation.dto.request.ChatRequestDto;
import com.example.m10.domain.node.presentation.dto.response.ChatResponseDto;
import com.example.m10.domain.node.service.common.define.NodeOutput;
import com.example.m10.domain.node.service.pipeline.Pipeline;
import com.example.m10.domain.node.service.pipeline.dto.PipelineStart;
import com.example.m10.domain.project.domain.Project;
import com.example.m10.domain.project.facade.ProjectFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CreateChatMessageTriggerService {

    private static final String NOT_FOUND_RESPONSE = "NOT FOUND";

    private final NodeRepository nodeRepository;
    private final PortRepository portRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ProjectFacade projectFacade;
    private final Pipeline pipeline;

    @Transactional
    public ChatResponseDto trigger(String startOutPortId, Long projectId, ChatRequestDto dto){
        Project project = projectFacade.findByProject(projectId);
        validateStartPort(project, startOutPortId);

        String userMessage = dto.message();
        Map<String, Object> attrs = Map.of(Role.HUMAN.name(), userMessage);
        saveMessage(project, Role.HUMAN, userMessage);

        List<NodeOutput> outs = pipeline.run(new PipelineStart(
                projectId,
                UUID.fromString(startOutPortId),
                attrs,
                TriggerType.CHAT_MESSAGE
        ));

        return extractAiChat(outs)
                .map(response -> {
                    saveMessage(project, Role.AI, response.message());
                    return response;
                })
                .orElseGet(() -> {
                    saveMessage(project, Role.OTHER, NOT_FOUND_RESPONSE);
                    return ChatResponseDto.from(Role.OTHER.name(), NOT_FOUND_RESPONSE);
                });
    }


    private void validateStartPort(Project project, String outPortUuid){
        Node chatNode = nodeRepository.findByProjectAndName(project, NodeName.CHAT)
                .orElseThrow(ChatNodeNotFoundException::new);

        portRepository.findByNode(chatNode)
                .map(Port::getOutPortId)
                .filter(p -> p.equals(UUID.fromString(outPortUuid)))
                .orElseThrow(InvalidStartPortException::new);
    }

    private void saveMessage(Project project, Role role, String content){
        chatMessageRepository.save(
                ChatMessage.builder()
                        .project(project)
                        .role(role)
                        .content(content)
                        .build()
        );
    }

    private Optional<ChatResponseDto> extractAiChat(List<NodeOutput> outs){
        if (outs == null || outs.isEmpty()) return Optional.empty();

        return outs.stream()
                .filter(NodeOutput.Chat.class::isInstance)
                .map(NodeOutput.Chat.class::cast)
                .map(NodeOutput.Chat::response)
                .findFirst();//응답은 무조건 하나임
    }
}
