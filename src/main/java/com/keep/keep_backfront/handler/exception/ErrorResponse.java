package com.keep.keep_backfront.handler.exception;

import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.util.HashMap;

public class ErrorResponse {

    private Integer code;
    private Integer status;
    private String path;
    private String message;
    private String detailMessage;
    private Instant timestamp;
    private HashMap<String, Object> data = new HashMap<>();

    public ErrorResponse() {
    }

    public ErrorResponse(BaseException ex, String path) {
        this(ex.getError().getCode(), ex.getError().getStatus().value(), path, ex.getError().getMessage(), ex.getMessage(), ex.getData());
    }

    public ErrorResponse(Integer code, Integer status, String path, String message, String detailMessage, HashMap<String, Object> data) {
        this.code = code;
        this.status = status;
        this.path = path;
        this.message = message;
        this.detailMessage = detailMessage;

        if (!ObjectUtils.isEmpty(data)) {
            this.data = data;
        }
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
}
