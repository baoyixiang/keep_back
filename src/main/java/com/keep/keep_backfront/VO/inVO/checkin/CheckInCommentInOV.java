package com.keep.keep_backfront.VO.inVO.checkin;

import lombok.Data;

import java.util.Date;

@Data
public class CheckInCommentInOV {
    private Integer userId;
    private Integer checkInId;
    private Date commentTime;
    private String commentContent;
    private Integer replyTo;
}
