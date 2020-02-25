package com.keep.keep_backfront.VO.outVO.user;

import lombok.Data;

@Data
public class WxResponse {
    private String sessionKey;
    private String openId;
    private String unionId;
    private String errCode;
    private String errMsg;
}
