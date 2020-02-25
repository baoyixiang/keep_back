package com.keep.keep_backfront.VO.outVO.user;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserInfoVO {
    private Long id;
    private String userName;
    private String phone;
    private Integer status;
    private Timestamp registerTime;
    private String role;
    private String headImg;
    private Integer sex;
    private String location;
    private Timestamp birthday;
    private String openId;
    private String nickName;
}
