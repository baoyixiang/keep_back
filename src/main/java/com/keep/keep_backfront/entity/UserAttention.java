package com.keep.keep_backfront.entity;


import java.io.Serializable;
import java.util.Date;

public class UserAttention implements Serializable {

  private Integer id;
  private Integer userId;
  private Integer followedUserId;
  private Date followTime;


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

  public Integer getFollowedUserId() {
    return followedUserId;
  }

  public void setFollowedUserId(Integer followedUserId) {
    this.followedUserId = followedUserId;
  }

  public Date getFollowTime() {
    return followTime;
  }

  public void setFollowTime(Date followTime) {
    this.followTime = followTime;
  }
}
