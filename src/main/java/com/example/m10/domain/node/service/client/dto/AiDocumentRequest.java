package com.example.m10.domain.node.service.client.dto;

import lombok.Builder;

@Builder
public record AiDocumentRequest(
        int chunk_size,
        String embedding_model,
        String vector_db,
        String object_key
) {
}
