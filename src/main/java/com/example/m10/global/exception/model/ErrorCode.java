package com.example.m10.global.exception.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류 입니다."),

    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "로그인 세션이 만료되었습니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "리프레시 토큰이 유효하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "리프레시 토큰이 존재하지 않습니다."),

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 유저가 존재하지 않습니다."),
    USER_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 유저입니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 다릅니다."),

    PROJECT_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 프로젝트가 존재하지 않습니다."),

    FORBIDDEN_PROJECT_UPDATE(HttpStatus.FORBIDDEN, "프로젝트를 수정할 권한이 없습니다."), // 권한 관련된거라 빼놨느데 프젝으로 생각하며 붙여도 됨

    EDGE_LINK_FAILED(HttpStatus.NOT_FOUND, "포트를 연결할 수 없습니다."),
    CHAT_NODE_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 채팅 노드가 생성되었습니다."),
    EDGE_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 노드가 연결되어 있습니다."),
    NODE_NOT_FOUND(HttpStatus.NOT_FOUND, "노드가 존재하지 않습니다."),
    CHAT_NODE_NOT_FOUND(HttpStatus.NOT_FOUND, "채팅 노드가 존재하지 않습니다."),
    INVALID_CHAT_NODE_PORT(HttpStatus.BAD_REQUEST, "요청한 startOutPortId가 CHAT 노드의 OutPortId와 일치하지 않습니다."),

    OBJECT_KEY_NOT_FOUND(HttpStatus.NOT_FOUND, "Object Key가 존재하지 않습니다."),
    INTERNAL_S3_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "S3 에러 입니다."),

    UNSUPPORTED_FILE_EXTENSION(HttpStatus.BAD_REQUEST, "파일 확장자가 다릅니다."),
    FILE_IS_EMPTY(HttpStatus.BAD_REQUEST, "파일이 비워져있습니다."),

    DTO_CONVERSION_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "DTO 매핑에 실패했습니다."),

    NODE_DELETION_NOT_ALLOWED(HttpStatus.CONFLICT, "이 노드는 다른 노드와 연결되어 있어서 삭제 불가능합니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
