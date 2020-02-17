package com.keep.keep_backfront.entity;


import java.io.Serializable;
import java.util.Date;

public class CheckInComments implements Serializable {

  private Integer id;
  private Integer userId;
  private Integer checkInId;
  private Date commentTime;
  private String commentContent;
  private Integer replyTo;

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

  public Integer getCheckInId() {
    return checkInId;
  }

  public void setCheckInId(Integer checkInId) {
    this.checkInId = checkInId;
  }

  public Date getCommentTime() {
    return commentTime;
  }

  public void setCommentTime(Date commentTime) {
    this.commentTime = commentTime;
  }

  public String getCommentContent() {
    return commentContent;
  }

  public void setCommentContent(String commentContent) {
    this.commentContent = commentContent;
  }

  public Integer getReplyTo() {
    return replyTo;
  }

  public void setReplyTo(Integer replyTo) {
    this.replyTo = replyTo;
  }
}
