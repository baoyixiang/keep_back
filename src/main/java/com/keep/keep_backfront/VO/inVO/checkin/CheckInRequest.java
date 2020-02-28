package com.keep.keep_backfront.VO.inVO.checkin;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.List;

@Data
public class CheckInRequest {
    private Integer userId;
    private Integer customId;
    private String wordContent;
    private List<String> images;
    private String voice;

    @Override
    public String toString() {
        return "CheckInRequest{" +
                "userId=" + userId +
                ", customId=" + customId +
                ", wordContent='" + wordContent + '\'' +
                ", images=" + images +
                ", voice='" + voice + '\'' +
                '}';
    }
}
