package com.keep.keep_backfront.entity;


import com.alibaba.fastjson.JSONArray;
import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.Date;

public class Hope implements Serializable {

  private Integer id;
  private String wordContent;
  private JSONArray images = new JSONArray();
  private String voice;
  private Integer createUserId;
  private Date createTime;
  private Boolean isAnonymous;
  private Boolean isSeeSelf;
  private Integer likeCount;
  private Integer CommentCount;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getWordContent() {
    return wordContent;
  }

  public void setWordContent(String wordContent) {
    this.wordContent = wordContent;
  }

  public JSONArray getImages() {
    return images;
  }

  public void setImages(JSONArray images) {
    this.images = images;
  }

  public String getVoice() {
    return voice;
  }

  public void setVoice(String voice) {
    this.voice = voice;
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

  public Boolean getAnonymous() {
    return isAnonymous;
  }

  public void setAnonymous(Boolean anonymous) {
    isAnonymous = anonymous;
  }

  public Boolean getSeeSelf() {
    return isSeeSelf;
  }

  public void setSeeSelf(Boolean seeSelf) {
    isSeeSelf = seeSelf;
  }

  public Integer getLikeCount() {
    return likeCount;
  }

  public void setLikeCount(Integer likeCount) {
    this.likeCount = likeCount;
  }

  public Integer getCommentCount() {
    return CommentCount;
  }

  public void setCommentCount(Integer commentCount) {
    CommentCount = commentCount;
  }
}
