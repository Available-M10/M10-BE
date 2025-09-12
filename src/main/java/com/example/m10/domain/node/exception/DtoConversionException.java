package com.example.m10.domain.node.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class DtoConversionException extends CustomException {
    public DtoConversionException(){
        super(ErrorCode.DTO_CONVERSION_ERROR);
    }
}
