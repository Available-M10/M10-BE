package com.example.m10.domain.file.service;


import com.example.m10.domain.file.exception.FileIsEmptyException;
import com.example.m10.domain.file.presentation.dto.request.FileUploadRequest;
import com.example.m10.domain.file.presentation.dto.response.FileUploadUrlResponse;
import com.example.m10.domain.file.type.FileType;
import com.example.m10.domain.user.domain.User;
import com.example.m10.domain.user.facade.UserFacade;
import com.example.m10.infra.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final S3Service s3Service;
    private final UserFacade userFacade;
    private static final Duration DEFAULT_DURATION = Duration.ofMinutes(2);

    public FileUploadUrlResponse execute(FileUploadRequest request) {
        User user = userFacade.getCurrentUser();

        validateFileName(request.fileName());
        validateExtension(request.fileName(), request.fileType());

        String objectKey = generateObjectKey(request.fileType(), request.fileName(), user.getId());
        String url = s3Service.createUploadPresignedUrl(objectKey, DEFAULT_DURATION).toString();

        return new FileUploadUrlResponse(objectKey, url);
    }

    private void validateFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new FileIsEmptyException();
        }
    }

    private void validateExtension(String fileName, FileType fileType) {
        if (!fileType.isAllowedExtension(fileName)) {
            throw new UnsupportedOperationException();
        }
    }

    private String generateObjectKey(FileType fileType, String fileName, Long userId) {
        return fileType.getPath().toLowerCase() + "/" + userId + "/" + UUID.randomUUID() + "/" + fileName;
    }

}
