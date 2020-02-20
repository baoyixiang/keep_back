package com.keep.keep_backfront.VO.inVO.custom;

import lombok.Data;

import java.util.Date;

@Data
public class JoinCustomInVO {
    private Integer id;
    private Integer userId;
    private Integer customId;
    private Boolean isPublic;
    private Integer targetDay;
    private Boolean isCompleted;
    private Integer beansCount;
    private Integer checkDaysCount;

}
