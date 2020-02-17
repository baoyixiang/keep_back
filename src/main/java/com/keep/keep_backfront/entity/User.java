package com.keep.keep_backfront.entity;


import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {

  private Integer id;
  private String wechatId;
  private String avatar;
  private String name;
  private String personalSignature;
  private Integer gender;
  private Date lastLoginTime;
  private Integer beansCount;
  private JSONArray roles;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getWechatId() {
    return wechatId;
  }

  public void setWechatId(String wechatId) {
    this.wechatId = wechatId;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPersonalSignature() {
    return personalSignature;
  }

  public void setPersonalSignature(String personalSignature) {
    this.personalSignature = personalSignature;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Date getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public Integer getBeansCount() {
    return beansCount;
  }

  public void setBeansCount(Integer beansCount) {
    this.beansCount = beansCount;
  }

  public JSONArray getRoles() {
    return roles;
  }

  public void setRoles(JSONArray roles) {
    this.roles = roles;
  }
}
