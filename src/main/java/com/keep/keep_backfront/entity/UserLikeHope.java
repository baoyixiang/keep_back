package com.keep.keep_backfront.entity;


import com.keep.keep_backfront.enums.LikeState;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 心愿点赞实体类
 */

@Data
public class UserLikeHope implements Serializable {

  private Integer userId;
  private Integer hopeId;
  private Integer likeState = LikeState.UNLIKE.getCode(); //默认为不点赞
  private Date likeTime ;

}
