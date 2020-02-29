package com.keep.keep_backfront.VO.inVO.checkin;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.List;

@Data
public class CheckInRecord {
    private Integer checkInId;
    private String wordContent;
    private List<String> images;
    private String voice;
}
