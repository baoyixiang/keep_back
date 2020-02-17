package com.keep.keep_backfront.entity;


import java.io.Serializable;
import java.util.Date;

public class UserLikeHope implements Serializable {

  private Integer id;
  private Integer userId;
  private Integer hopeId;
  private Date likeTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getHopeId() {
    return hopeId;
  }

  public void setHopeId(Integer hopeId) {
    this.hopeId = hopeId;
  }

  public Date getLikeTime() {
    return likeTime;
  }

  public void setLikeTime(Date likeTime) {
    this.likeTime = likeTime;
  }
}
