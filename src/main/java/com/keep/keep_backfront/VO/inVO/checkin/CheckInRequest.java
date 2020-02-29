package com.keep.keep_backfront.VO.inVO.checkin;

import com.alibaba.fastjson.JSONArray;
import lombok.Data;

import java.util.List;

@Data
public class CheckInRequest {
    private Integer userId;
    private Integer customId;
}
