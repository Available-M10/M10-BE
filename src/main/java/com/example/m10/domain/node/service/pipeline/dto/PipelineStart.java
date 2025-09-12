package com.example.m10.domain.node.service.pipeline.dto;

import com.example.m10.domain.node.domain.enums.node.TriggerType;

import java.util.UUID;
import java.util.Map;

public record PipelineStart(
        Long projectId,
        UUID startOutPortId,
        Map<String, Object> attrs,
        TriggerType type
) {
}
