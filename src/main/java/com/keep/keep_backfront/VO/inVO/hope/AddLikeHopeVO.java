package com.keep.keep_backfront.VO.inVO.hope;

import com.keep.keep_backfront.handler.exception.ParameterErrorException;
import lombok.Data;

import java.util.Date;

@Data
public class AddLikeHopeVO {
    private Integer userId;
    private Integer hopeId;
    private Integer likeState;

    public void checkParam(){
        if(userId==null){
            throw new ParameterErrorException("用户id不能为空");
        }
        if(hopeId==null){
            throw new ParameterErrorException("心愿id不能为空");
        }
    }
}
