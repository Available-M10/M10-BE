package com.example.m10.domain.node.presentation.controller;

import com.example.m10.domain.node.mapper.JsonMapper;
import com.example.m10.domain.node.presentation.dto.request.DocumentNodeRequestDto;
import com.example.m10.domain.node.presentation.dto.request.LLMNodeRequestDto;
import com.example.m10.domain.node.presentation.dto.response.NodeResponse;
import com.example.m10.domain.node.presentation.dto.response.QueryAllNodeResponse;
import com.example.m10.domain.node.service.DeleteNodeService;
import com.example.m10.domain.node.service.QueryAllNodeService;
import com.example.m10.domain.node.service.kind.last.LLMNodeService;
import com.example.m10.domain.node.service.kind.middle.DocumentNodeService;
import com.example.m10.domain.node.service.kind.start.ChatNodeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/node")
public class NodeController {//TODO: SRP 원착에 따라 Controller 분리

    private final ChatNodeService chatNodeService;
    private final DocumentNodeService documentNodeService;
    private final LLMNodeService LLMNodeService;

    private final QueryAllNodeService queryAllNodeService;
    private final DeleteNodeService deleteNodeService;

    private final JsonMapper jsonMapper;

    //start
    @PostMapping("/{projectId}/start/chat")
    public NodeResponse createChatNode(@PathVariable Long projectId){
        return chatNodeService.createNode(projectId);
    }

    //middle
    @PostMapping("/{projectId}/middle/document")
    public NodeResponse createDocumentNode(
            @PathVariable Long projectId,
            @Valid @RequestBody DocumentNodeRequestDto dto,
            @RequestParam String linkPortId
            ){
        return documentNodeService.createNode(projectId, linkPortId, jsonMapper.toJson(dto));
    }

    //Last
    @PostMapping("/{projectId}/last/llm")
    public NodeResponse createLLMNode(
            @PathVariable Long projectId,
            @Valid @RequestBody LLMNodeRequestDto dto,
            @RequestParam String linkPortId
    ){
        return LLMNodeService.createNode(projectId, linkPortId, jsonMapper.toJson(dto));
    }

    @GetMapping("/{projectId}")
    public List<QueryAllNodeResponse> getAllNode(@PathVariable Long projectId){
        return queryAllNodeService.getAllNode(projectId);
    }

    @DeleteMapping("/{nodeId}")
    public void delete(@PathVariable Long nodeId){
        deleteNodeService.delete(nodeId);
    }
}
