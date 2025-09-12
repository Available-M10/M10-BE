package com.example.m10.domain.file.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class FileIsEmptyException extends CustomException {
    public FileIsEmptyException(){
        super(ErrorCode.FILE_IS_EMPTY);
    }
}
