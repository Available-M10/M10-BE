package com.example.m10.global.security.jwt.exception;

import com.example.m10.global.model.CustomException;
import com.example.m10.global.model.ErrorCode;

public class ExpiredJwtException extends CustomException {
    public ExpiredJwtException(){
        super(ErrorCode.EXPIRED_JWT);
    }
}
