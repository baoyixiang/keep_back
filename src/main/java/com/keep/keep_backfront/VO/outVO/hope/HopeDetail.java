package com.keep.keep_backfront.VO.outVO.hope;

import com.alibaba.fastjson.JSONArray;
import com.keep.keep_backfront.entity.Hope;
import com.keep.keep_backfront.entity.HopeComment;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class HopeDetail implements Serializable {

    private List<HopeComment> Hopecomments;  //一条心愿可以包含多条评论
    private Hope hope;
    private boolean isLiked = false;

    @Override
    public String toString() {
        return "HopeDetail{" +
                "Hopecomments=" + Hopecomments +
                ", hope=" + hope +
                ", isLiked=" + isLiked +
                '}';
    }
}
