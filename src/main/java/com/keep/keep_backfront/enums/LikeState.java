package com.keep.keep_backfront.enums;

import lombok.Getter;

@Getter
public enum LikeState {
    LIKE(1,"点赞"),
    UNLIKE(0,"取消点赞");

    private Integer code;
    private String description;

    LikeState(Integer code,String description){
        this.code = code;
        this.description = description;
    }
}
