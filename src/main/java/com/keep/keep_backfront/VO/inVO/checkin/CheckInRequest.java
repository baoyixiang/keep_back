package com.keep.keep_backfront.VO.inVO.checkin;

import lombok.Data;

@Data
public class CheckInRequest {
    private Integer userId;
    private Integer customId;
    private String wordContent;
    private String images;
    private String voice;
}
