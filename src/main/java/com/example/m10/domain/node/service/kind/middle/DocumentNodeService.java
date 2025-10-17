package com.example.m10.domain.node.service.kind.middle;

import com.example.m10.domain.node.exception.DtoConversionException;
import com.example.m10.domain.node.presentation.dto.request.DocumentNodeRequestDto;
import com.example.m10.domain.node.presentation.dto.response.NodeResponse;
import com.example.m10.domain.node.service.client.AiClient;
import com.example.m10.domain.node.service.client.dto.AiDocumentRequest;
import com.example.m10.domain.node.service.common.MiddleNodeCreator;
import com.example.m10.domain.node.service.common.define.CommonNode;
import com.example.m10.domain.node.service.common.define.CommonRunNode;
import com.example.m10.domain.node.service.common.define.NodeOutput;
import com.example.m10.domain.node.service.common.define.RunContext;
import com.example.m10.infra.s3.S3Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class DocumentNodeService implements CommonNode, CommonRunNode {
    private final MiddleNodeCreator middleNode;
    private final S3Service s3Service;
    private final ObjectMapper objectMapper;
    private final AiClient aiClient;

    @Override
    @Transactional
    public NodeResponse createNode(Long projectId, String previousPortId, String json){
        return middleNode.createNode(projectId, previousPortId, json);
    }

    @Override
    @Transactional
    public NodeOutput run(RunContext ctx) {
        String json = ctx.node().getConfigJson();
        DocumentNodeRequestDto dto;
        try{
            dto = objectMapper.readValue(json, DocumentNodeRequestDto.class);
        } catch (JsonProcessingException e){
            throw new DtoConversionException();
        }

        String fileUrl = s3Service.generateUrl(dto.objectKey());
        AiDocumentRequest request = AiDocumentRequest.builder()
                .chunk_size(dto.chunkSize())
                .embedding_model(dto.embeddingModel().name())
                .vector_db(dto.vectorDB().name())
                .object_key(fileUrl)
                .build();

        aiClient.sendDocumentRequest(ctx.projectId(), request);
        return NodeOutput.none();
    }
}
