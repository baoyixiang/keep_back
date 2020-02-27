package com.keep.keep_backfront.entity;


import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;
import java.util.Date;

public class Custom implements Serializable {

  private Integer id;
  private String title="";
  private String logo="";
  private Integer createUserId=-1;
  private Date createTime=new Date();
  private JSONArray tags;
  private Boolean isDefault=false;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLogo() {
    return logo;
  }

  public void setLogo(String logo) {
    this.logo = logo;
  }

  public Integer getCreateUserId() {
    return createUserId;
  }

  public void setCreateUserId(Integer createUserId) {
    this.createUserId = createUserId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public JSONArray getTags() {
    return tags;
  }

  public void setTags(JSONArray tags) {
    this.tags = tags;
  }

  public Boolean getDefault() {
    return isDefault;
  }

  public void setDefault(Boolean aDefault) {
    isDefault = aDefault;
  }
}
