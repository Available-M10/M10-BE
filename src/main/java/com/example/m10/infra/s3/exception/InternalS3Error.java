package com.example.m10.infra.s3.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class InternalS3Error extends CustomException {
    public InternalS3Error(){
        super(ErrorCode.INTERNAL_S3_ERROR);
    }
}
