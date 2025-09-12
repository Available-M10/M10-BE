package com.example.m10.domain.file.presentation;

import com.example.m10.domain.file.presentation.dto.request.FileUploadRequest;
import com.example.m10.domain.file.presentation.dto.response.FileUploadUrlResponse;
import com.example.m10.domain.file.service.FileUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final FileUploadService fileUploadService;

    @PostMapping("/pre-signed")
    @ResponseStatus(HttpStatus.CREATED)
    public FileUploadUrlResponse createPresignedUrl(@Valid @RequestBody FileUploadRequest request){
        return fileUploadService.execute(request);
    }
}
