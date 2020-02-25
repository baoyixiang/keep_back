package com.keep.keep_backfront.VO.inVO.custom;

import lombok.Data;

import java.util.List;

@Data
public class RecommendListInVO {
    private Integer userId;
    private List<String> tagsList;
}
