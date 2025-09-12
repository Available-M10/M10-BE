package com.example.m10.domain.file.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@Getter
@AllArgsConstructor
public enum FileType {
    PDF_FILE("PDF_FILE", Set.of(".pdf"));

    private final String path;
    private final Set<String> extensions;

    public boolean isAllowedExtension(String fileName){
        String extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
        return extensions.contains(extension);
    }
}
