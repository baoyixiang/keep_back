package com.keep.keep_backfront.handler.exception;

import java.util.HashMap;
import java.util.Map;

public class ParameterErrorException extends BaseException {

    public ParameterErrorException(String detailMessage) {
        super(ErrorCode.PARAMETER_ERROR, null, detailMessage);
    }

    public ParameterErrorException(HashMap<String, Object> data) {
        super(ErrorCode.PARAMETER_ERROR, data);
    }

    public ParameterErrorException(HashMap<String, Object> data, String detailMessage) {
        super(ErrorCode.PARAMETER_ERROR, data, detailMessage);
    }
}
