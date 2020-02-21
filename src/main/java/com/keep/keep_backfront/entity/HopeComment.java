package com.keep.keep_backfront.entity;


import java.io.Serializable;
import java.util.Date;

public class HopeComment implements Serializable {

  private Integer id;
  private Integer userId;
  private Integer hopeId;
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

  public Integer getHopeId() {
    return hopeId;
  }

  public void setHopeId(Integer hopeId) {
    this.hopeId = hopeId;
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
