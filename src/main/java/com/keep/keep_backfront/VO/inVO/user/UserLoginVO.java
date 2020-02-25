package com.keep.keep_backfront.VO.inVO.user;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.Date;

@Data
public class UserLoginVO {
    private String code;
    private String avatarUrl;
    private String nickName;
    private String personalSignature;
    private Integer gender;
//
//    public void checkParam(){
//
//    }
//
//    @Override
//    public String toString() {
//        return "UserLoginVO{" +
//                ", wechatId='" + wechatId + '\'' +
//                ", avatar='" + avatar + '\'' +
//                ", name='" + name + '\'' +
//                ", personalSignature='" + personalSignature + '\'' +
//                ", gender=" + gender +
//                ", roles=" + roles +
//                '}';
//    }
}
