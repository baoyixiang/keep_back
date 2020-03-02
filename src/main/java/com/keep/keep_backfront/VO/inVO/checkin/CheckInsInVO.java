package com.keep.keep_backfront.VO.inVO.checkin;

import lombok.Data;

@Data
public class CheckInsInVO {
    private Integer userId;
    private Integer myUserId;
    private Integer customId;
    private Integer pageNo;
    private Integer PageSize;
}
