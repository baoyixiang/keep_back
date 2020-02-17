package com.keep.keep_backfront.handler.exception;

import org.springframework.util.ObjectUtils;

import java.util.HashMap;

public abstract class BaseException extends RuntimeException {

    private final ErrorCode error;
    private final HashMap<String, Object> data = new HashMap<>();


    public BaseException(ErrorCode error, HashMap<String, Object> data) {
        this.error = error;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    public BaseException(ErrorCode error, HashMap<String, Object> data, String detailMessage) {
        super(detailMessage);
        this.error = error;
        if (!ObjectUtils.isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    public ErrorCode getError() {
        return error;
    }

    public HashMap<String, Object> getData() {
        return data;
    }
}
