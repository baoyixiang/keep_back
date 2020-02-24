package com.keep.keep_backfront.VO.outVO.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserListVO {
    private String wechatId;
    private String avatar;
    private String name;
    private String personalSignature;
    private Integer gender;
    private Date lastLoginTime;
    private Integer beansCount;

}
