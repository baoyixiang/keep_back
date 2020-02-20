package com.keep.keep_backfront.VO.outVO.custom;

import com.keep.keep_backfront.entity.CheckIn;
import com.keep.keep_backfront.entity.JoinCustom;
import lombok.Data;

import java.util.List;

@Data
public class CustomDetailOutVO {
    private JoinCustom joinCustom;
    private List<CheckIn> checkInList;
}
