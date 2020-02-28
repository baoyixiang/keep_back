package com.keep.keep_backfront.VO.outVO.checkIn;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class CheckInOutVO {
    private Integer checkInId;
    private String userName;
    private Integer commentCount;
    private Integer likeCount;
    private Date checkInTime;
    private String wordContent;
}
