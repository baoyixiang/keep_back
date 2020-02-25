package com.keep.keep_backfront.VO.inVO.user;

import com.keep.keep_backfront.handler.exception.ParameterErrorException;
import lombok.Data;

@Data
public class UserFollowInVO {
    private Integer userId;
    private Integer followedUserId;

    public void checkParam(){
        if(userId==null){
            throw new ParameterErrorException("用户id不能为空");
        }
        if(followedUserId==null){
            throw new ParameterErrorException("心愿id不能为空");
        }
    }
}
