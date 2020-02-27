package com.keep.keep_backfront.VO.inVO.custom;

import lombok.Data;

@Data
public class CustomListInVO {
    private Integer userId;
    private String title;
    private Boolean isDefault;
    private Integer pageNo;
    private Integer pageSize;
}
