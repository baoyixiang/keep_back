package com.keep.keep_backfront.VO.outVO.custom;

import com.keep.keep_backfront.entity.Custom;
import lombok.Data;

import java.util.List;

@Data
public class RecommendCustomOutVO {
    public String title;
    public List<Custom> customList;
}
