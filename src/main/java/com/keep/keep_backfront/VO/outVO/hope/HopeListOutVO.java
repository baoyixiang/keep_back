package com.keep.keep_backfront.VO.outVO.hope;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.Date;

@Data
public class HopeListOutVO {
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

    private String avatar;
    private String name;

    @Override
    public String toString() {
        return "HopeListOutVO{" +
                "id=" + id +
                ", wordContent='" + wordContent + '\'' +
                ", images=" + images +
                ", voice='" + voice + '\'' +
                ", createUserId=" + createUserId +
                ", createTime=" + createTime +
                ", isAnonymous=" + isAnonymous +
                ", isSeeSelf=" + isSeeSelf +
                ", likeCount=" + likeCount +
                ", CommentCount=" + CommentCount +
                ", avatar='" + avatar + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
