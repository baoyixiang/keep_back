package com.keep.keep_backfront.VO.outVO.custom;

import com.keep.keep_backfront.entity.Custom;
import com.keep.keep_backfront.entity.JoinCustom;
import lombok.Data;

@Data
public class CustomListOutVO {
    private Custom custom;
    private JoinCustom joinCustom;
}
