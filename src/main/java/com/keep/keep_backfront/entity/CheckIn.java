package com.keep.keep_backfront.entity;


import com.alibaba.fastjson.JSONArray;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CheckIn implements Serializable {

  private Integer id;
  private Integer userId;
  private Integer customeId;
  private Date checkInTime;
  private String wordContent;
  private JSONArray images = new JSONArray();
  private String voice;
  private Integer days;

  @Override
  public String toString() {
    return "CheckIn{" +
            "id=" + id +
            ", userId=" + userId +
            ", customeId=" + customeId +
            ", checkInTime=" + checkInTime +
            ", wordContent='" + wordContent + '\'' +
            ", images=" + images +
            ", voice='" + voice + '\'' +
            ", days=" + days +
            '}';
  }

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

  public Integer getCustomeId() {
    return customeId;
  }

  public void setCustomeId(Integer customeId) {
    this.customeId = customeId;
  }

  public Date getCheckInTime() {
    return checkInTime;
  }

  public void setCheckInTime(Date checkInTime) {
    this.checkInTime = checkInTime;
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

  public Integer getDays() {
    return days;
  }

  public void setDays(Integer days) {
    this.days = days;
  }
}
