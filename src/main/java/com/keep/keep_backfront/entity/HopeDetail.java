package com.keep.keep_backfront.entity;

import com.alibaba.fastjson.JSONArray;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HopeDetail implements Serializable {
    private String wordContent;
    private List<HopeComment> Hopecomments;  //一条心愿可以包含多条评论
    private JSONArray images = new JSONArray();
    private Integer likeCount;
    private Integer CommentCount;
    private Integer hopeId;
}
