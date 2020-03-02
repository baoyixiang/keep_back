package com.keep.keep_backfront.VO.outVO.checkIn;

import com.keep.keep_backfront.entity.CheckIn;
import com.keep.keep_backfront.entity.CheckInComments;
import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.entity.UserLikeCheckIn;
import lombok.Data;

import java.util.List;

@Data
public class CheckInDetail {
    private CheckIn checkIn;
    private List<CheckInCommentsOutVO> checkInComments;
    private List<User> likeUsers;
    private Boolean isSelfLike;
    private User user;
    private String customTitle;
}
