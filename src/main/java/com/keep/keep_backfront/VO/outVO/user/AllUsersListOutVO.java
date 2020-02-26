package com.keep.keep_backfront.VO.outVO.user;

import com.keep.keep_backfront.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class AllUsersListOutVO {
    private User user;
    private Integer customesCount;
    private Integer hopesCount;
    private Integer followedCount;
    private Integer followingCount;

    @Override
    public String toString() {
        return "AllUsersListOutVO{" +
                "user=" + user +
                ", customesCount=" + customesCount +
                ", hopesCount=" + hopesCount +
                ", followedCount=" + followedCount +
                ", followingCount=" + followingCount +
                '}';
    }
}
