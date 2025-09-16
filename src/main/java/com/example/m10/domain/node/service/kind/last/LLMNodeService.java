package com.example.m10.domain.node.service.kind.last;

import com.example.m10.domain.node.domain.enums.chat.Role;
import com.example.m10.domain.node.exception.DtoConversionException;
import com.example.m10.domain.node.presentation.dto.request.LLMNodeRequestDto;
import com.example.m10.domain.node.presentation.dto.response.ChatResponseDto;
import com.example.m10.domain.node.presentation.dto.response.NodeResponse;
import com.example.m10.domain.node.service.common.CommonLastNode;
import com.example.m10.domain.node.service.common.define.CommonNode;
import com.example.m10.domain.node.service.common.define.CommonRunNode;
import com.example.m10.domain.node.service.common.define.NodeOutput;
import com.example.m10.domain.node.service.common.define.RunContext;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class LLMNodeService implements CommonNode, CommonRunNode {
    private final CommonLastNode lastNode;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public NodeResponse createNode(Long projectId, String previousPortId, String json){
        return lastNode.createNode(projectId, previousPortId, json);
    }


    @Override
    public NodeOutput run(RunContext ctx) {
        String json = ctx.node().getConfigJson();
        LLMNodeRequestDto dto;
        try{
            dto = objectMapper.readValue(json, LLMNodeRequestDto.class);
        } catch (JsonProcessingException e){
            throw new DtoConversionException();
        }

        //TODO: AI와 연결 후 LLM 요청 보내어 답변 받기 구현
        String message = ctx.attrs().get(Role.HUMAN.name()).toString();
        System.out.println("llm: "+dto.chatNode() + ", prompt: " + dto.prompt()+"message: "+message);

        return NodeOutput.chat(ChatResponseDto.from(Role.AI.name(), "TEXT"));
    }
}
