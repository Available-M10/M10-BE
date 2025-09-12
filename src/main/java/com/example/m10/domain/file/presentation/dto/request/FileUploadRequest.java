package com.example.m10.domain.file.presentation.dto.request;

import com.example.m10.domain.file.type.FileType;
import jakarta.validation.constraints.NotNull;

public record FileUploadRequest(
        @NotNull
        FileType fileType,

        @NotNull
        String fileName
) {
}
