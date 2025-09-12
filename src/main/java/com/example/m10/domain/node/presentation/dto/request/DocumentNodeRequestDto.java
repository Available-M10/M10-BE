package com.example.m10.domain.node.presentation.dto.request;

import com.example.m10.domain.node.domain.enums.ai.EmbeddingModel;
import com.example.m10.domain.node.domain.enums.ai.VectorDB;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DocumentNodeRequestDto(
        @NotNull
        Integer chunkSize,

        @NotNull
        EmbeddingModel embeddingModel,

        @NotNull
        VectorDB vectorDB,

        @NotBlank
        String objectKey
) {
}
