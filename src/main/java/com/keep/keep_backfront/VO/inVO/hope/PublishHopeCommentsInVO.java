package com.keep.keep_backfront.VO.inVO.hope;

import com.keep.keep_backfront.handler.exception.ParameterErrorException;
import lombok.Data;

/**
 * hope评论请求：需要评论者id、对应hopeid以及评论内容
 */

@Data
public class PublishHopeCommentsInVO {
    private Integer userId;
    private Integer hopeId;
    private String commentContent;
    private Integer replyTo;

    public void checkParam(){
        if(commentContent==null){
            throw new ParameterErrorException("评论内容不能为空");
        }
    }
}
