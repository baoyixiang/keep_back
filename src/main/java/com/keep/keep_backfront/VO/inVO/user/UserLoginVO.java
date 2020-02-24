package com.keep.keep_backfront.VO.inVO.user;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.Date;

@Data
public class UserLoginVO {
//    private Integer id;
    private String wechatId;
    private String avatar;
    private String name;
    private String personalSignature;
    private Integer gender;
//    private Date lastLoginTime;
//    private Integer beansCount;
    private JSONArray roles;

    public void checkParam(){

    }

    @Override
    public String toString() {
        return "UserLoginVO{" +
                ", wechatId='" + wechatId + '\'' +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                ", personalSignature='" + personalSignature + '\'' +
                ", gender=" + gender +
                ", roles=" + roles +
                '}';
    }
}
