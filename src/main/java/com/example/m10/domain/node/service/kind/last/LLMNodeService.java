package com.example.m10.domain.node.service.kind.last;

import com.example.m10.domain.node.domain.enums.chat.Role;
import com.example.m10.domain.node.mapper.JsonMapper;
import com.example.m10.domain.node.presentation.dto.request.LLMNodeRequestDto;
import com.example.m10.domain.node.presentation.dto.response.ChatResponseDto;
import com.example.m10.domain.node.presentation.dto.response.NodeResponse;
import com.example.m10.domain.node.service.client.AiClient;
import com.example.m10.domain.node.service.client.dto.AiLLMRequest;
import com.example.m10.domain.node.service.common.LastNodeCreator;
import com.example.m10.domain.node.service.common.define.CommonNode;
import com.example.m10.domain.node.service.common.define.CommonRunNode;
import com.example.m10.domain.node.service.common.define.NodeOutput;
import com.example.m10.domain.node.service.common.define.RunContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class LLMNodeService implements CommonNode, CommonRunNode {
    private final LastNodeCreator lastNode;
    private final JsonMapper jsonMapper;
    private final AiClient aiClient;

    @Override
    @Transactional
    public NodeResponse createNode(Long projectId, String previousPortId, String json){
        return lastNode.createNode(projectId, previousPortId, json);
    }


    @Override
    public NodeOutput run(RunContext ctx) {
        String json = ctx.node().getConfigJson();
        LLMNodeRequestDto dto = jsonMapper.fromJson(json, LLMNodeRequestDto.class);

        String message = ctx.attrs().get(Role.HUMAN.name()).toString();
        AiLLMRequest request = AiLLMRequest.builder()
                .llm(dto.chatNode().name())
                .prompt(dto.prompt())
                .message(message)
                .topK(5) //임의의 값
                .build();

        String ai_message = aiClient.sendLlmRequest(ctx.projectId(), request);

        return NodeOutput.chat(ChatResponseDto.from(Role.AI.name(), ai_message));
    }
}
