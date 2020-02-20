package com.keep.keep_backfront.VO.inVO.hope;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class HopeListInVO {
    private Integer userId;
    private Integer pageNo;
    private Integer pageSize;
}
