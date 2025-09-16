package com.example.m10.domain.file.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class UnsupportedFileExtension extends CustomException {
    public UnsupportedFileExtension(){
        super(ErrorCode.UNSUPPORTED_FILE_EXTENSION);
    }
}
