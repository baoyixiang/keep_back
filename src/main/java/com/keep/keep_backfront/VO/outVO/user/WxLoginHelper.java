package com.keep.keep_backfront.VO.outVO.user;

import com.alibaba.fastjson.JSONObject;
import com.keep.keep_backfront.util.HttpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WxLoginHelper {

    public WxResponse loginWx(String code) {
        String wxspAppid = "wx8b2d8f761f990064";
        //小程序的 app secret (在微信小程序管理后台获取)
        String wxspSecret = "c27bfefcb7d983b269441abeddc53880";
        //授权（必填）
        String grant_type = "authorization_code";
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
        //解析相应内容（转换成json对象）
        WxResponse wxResponse = new WxResponse();
        JSONObject json = JSONObject.parseObject(sr);
        if(json.get("session_key") != null) {
            wxResponse.setSessionKey(json.get("session_key").toString());
        }
        if(json.get("openid") != null) {
            wxResponse.setOpenId(json.get("openid").toString());
        }
        if(json.get("unionId") != null) {
            wxResponse.setUnionId(json.get("unionId").toString());
        }
        if(json.get("errCode") != null) {
            wxResponse.setErrCode(json.get("errCode").toString());
        }
        if(json.get("errMsg") != null) {
            wxResponse.setErrMsg(json.get("errMsg").toString());
        }
        return wxResponse;
    }


}
