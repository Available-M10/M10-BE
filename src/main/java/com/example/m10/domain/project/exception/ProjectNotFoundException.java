package com.example.m10.domain.project.exception;

import com.example.m10.global.exception.model.CustomException;
import com.example.m10.global.exception.model.ErrorCode;

public class ProjectNotFoundException extends CustomException {
    public ProjectNotFoundException(){
        super(ErrorCode.PROJECT_NOT_FOUND);
    }
}
