package com.keep.keep_backfront.VO.inVO.custom;

import com.keep.keep_backfront.handler.exception.ParameterErrorException;
import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class AddCustomInVO {
    private String title;
    private Integer userId;

    public void checkParam() {
        if (StringUtils.isEmpty(title))
            throw new ParameterErrorException("习惯标题不能为空");
        if (userId == null)
            throw new ParameterErrorException("用户Id不能为空");
    }
}
