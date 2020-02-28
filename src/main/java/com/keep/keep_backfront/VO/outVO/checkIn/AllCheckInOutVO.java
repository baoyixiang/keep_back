package com.keep.keep_backfront.VO.outVO.checkIn;

import lombok.Data;

import java.util.List;

@Data
public class AllCheckInOutVO {
    private List<CheckInOutVO> checkInOutVOS;
    private Integer checkInToday;
    private Integer joinCount;
}
