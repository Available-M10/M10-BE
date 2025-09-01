package com.example.m10.domain.auth.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class PasswordMisMatchException extends CustomException {
    public PasswordMisMatchException(){
        super(ErrorCode.PASSWORD_MISMATCH);
    }
}
