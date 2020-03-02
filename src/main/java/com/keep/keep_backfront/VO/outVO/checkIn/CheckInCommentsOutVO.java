package com.keep.keep_backfront.VO.outVO.checkIn;

import lombok.Data;

import java.util.Date;

@Data
public class CheckInCommentsOutVO {

    private Integer userId;
    private String userName;
    private String userAvatar;
    private Integer checkInId;
    private Date commentTime;
    private String commentContent;
    private Integer replyTo;
    private String replyToName;
    private String replyToAvatar;
}
