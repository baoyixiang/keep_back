package com.keep.keep_backfront.entity;


import java.io.Serializable;
import java.util.Date;

public class JoinCustom implements Serializable {

  private Integer id;
  private Integer userId;
  private Integer customId;
  private Date joinTime;
  private Boolean isPublic;
  private Integer targetDays;
  private Boolean isCompleted;
  private Integer beansCount;
  private Integer checkDaysCount;


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

  public Integer getCustomId() {
    return customId;
  }

  public void setCustomId(Integer customId) {
    this.customId = customId;
  }

  public Date getJoinTime() {
    return joinTime;
  }

  public void setJoinTime(Date joinTime) {
    this.joinTime = joinTime;
  }

  public Boolean getPublic() {
    return isPublic;
  }

  public void setPublic(Boolean aPublic) {
    isPublic = aPublic;
  }

  public Integer getTargetDays() {
    return targetDays;
  }

  public void setTargetDays(Integer targetDays) {
    this.targetDays = targetDays;
  }

  public Boolean getCompleted() {
    return isCompleted;
  }

  public void setCompleted(Boolean completed) {
    isCompleted = completed;
  }

  public Integer getBeansCount() {
    return beansCount;
  }

  public void setBeansCount(Integer beansCount) {
    this.beansCount = beansCount;
  }

  public Integer getCheckDaysCount() {
    return checkDaysCount;
  }

  public void setCheckDaysCount(Integer checkDaysCount) {
    this.checkDaysCount = checkDaysCount;
  }
}
