package com.keep.keep_backfront.VO.inVO.checkin;

import lombok.Data;

import java.util.Date;

@Data
public class LikeCheckInOV {
    private Integer userId;
    private Integer checkInId;
    private Date likeTime;
}
