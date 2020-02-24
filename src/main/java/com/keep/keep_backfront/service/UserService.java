package com.keep.keep_backfront.service;

import com.keep.keep_backfront.VO.outVO.user.UserHomeVO;
import com.keep.keep_backfront.VO.inVO.user.UserLoginVO;
import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.entity.UserAttention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     *  用户登录
     */
    public ResponseEntity<Object> login(UserLoginVO userLoginVO){
        User curUser = userDao.getUserById(userLoginVO.getWechatId());
        if(curUser==null){  //登录的用户不存在,则将用户信息插入数据库
            try {
                int effectedNum = 0;

                User user = new User();
                user.setWechatId(userLoginVO.getWechatId());
                user.setAvatar(userLoginVO.getAvatar());
                user.setName(userLoginVO.getName());
                user.setPersonalSignature(userLoginVO.getPersonalSignature());
                user.setGender(userLoginVO.getGender());
                user.setLastLoginTime(new Date());
                user.setBeansCount(0);
                user.setRecommended(false);
                effectedNum = userDao.newUser(user);

                if (effectedNum == 1) {
                    return ResponseEntity.status(HttpStatus.OK).build();
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 查看用户主页
     */
    public UserHomeVO userHome(Integer userId){
        UserHomeVO userHomeVO = new UserHomeVO();

        return userHomeVO;
    }

    /**
     * 关注用户
     */
    public void attentionUser(Integer userId,Integer followedUserId){
        UserAttention userAttention = new UserAttention();
        userAttention.setUserId(userId);
        userAttention.setFollowedUserId(followedUserId);
        userAttention.setFollowTime(new Date());
        try{
            userDao.insertFollowing(userAttention);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("关注用户错误");
        }
    }

}
