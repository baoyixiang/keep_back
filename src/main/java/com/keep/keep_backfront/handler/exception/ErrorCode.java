package com.keep.keep_backfront.handler.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    PARAMETER_ERROR(100, HttpStatus.BAD_REQUEST, "请求数据格式验证失败");


    private final Integer code;

    private final HttpStatus status;

    private final String message;

    ErrorCode(Integer code, HttpStatus status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
