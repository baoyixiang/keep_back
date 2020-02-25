package com.keep.keep_backfront.service;

import com.keep.keep_backfront.VO.inVO.user.UserFollowInVO;
import com.keep.keep_backfront.VO.outVO.user.UserHomeOutVO;
import com.alibaba.fastjson.JSONArray;
import com.keep.keep_backfront.VO.outVO.user.*;
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

    private UserDao userDao;

    private WxLoginHelper wxLoginHelper;

    public UserService(WxLoginHelper wxLoginHelper) {
        this.wxLoginHelper = wxLoginHelper;
    }

    /**
     *  用户登录
     */
    public User login(UserLoginVO userLoginVO){
        User user = new User();
        WxResponse wxResponse = wxLoginHelper.loginWx(userLoginVO.getCode());//报空指针异常
        User curUser = userDao.getUserByWechatId(wxResponse.getOpenId());
        if(curUser == null) {
            try {
                String openId = wxResponse.getOpenId();
                user.setWechatId(openId);
                user.setAvatar(userLoginVO.getAvatarUrl());
                user.setName(userLoginVO.getNickName());
                user.setPersonalSignature(userLoginVO.getPersonalSignature());
                user.setGender(userLoginVO.getGender());
                user.setLastLoginTime(new Date());
                user.setBeansCount(0);
                user.setRoles(new JSONArray());
                //user.setRecommended(false);
                userDao.newUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        user = userDao.getUserByWechatId(wxResponse.getOpenId());
        return user;

//        if(curUser==null){  //登录的用户不存在,则将用户信息插入数据库
//            try {
//                int effectedNum = 0;
//
//                User user = new User();
//                user.setWechatId(userLoginVO.getWechatId());
//                user.setAvatar(userLoginVO.getAvatar());
//                user.setName(userLoginVO.getName());
//                user.setPersonalSignature(userLoginVO.getPersonalSignature());
//                user.setGender(userLoginVO.getGender());
//                user.setLastLoginTime(new Date());
//                user.setBeansCount(0);
//                user.setRecommended(false);
//                effectedNum = userDao.newUser(user);
//
//                if (effectedNum == 1) {
//                    return ResponseEntity.status(HttpStatus.OK).build();
//                } else {
//                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//                }
//            } catch (Exception ex) {
//                ex.printStackTrace();
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        }
//        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * 查看用户主页
     */
    public UserHomeOutVO userHome(Integer userId){
        UserHomeOutVO userHomeOutVO = new UserHomeOutVO();

        return userHomeOutVO;
    }

    /**
     * 关注用户
     */
    public ResponseEntity attentionUser(UserFollowInVO userFollowInVO){
        try {
            int effectedNum = 0;

            UserAttention userAttention = new UserAttention();
            userAttention.setUserId(userFollowInVO.getUserId());
            userAttention.setFollowedUserId(userFollowInVO.getFollowedUserId());
            userAttention.setFollowTime(new Date());

            effectedNum = userDao.insertFollowing(userAttention);

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

    /**
     * 取消关注
     * @return
     */
    public ResponseEntity cancelFollow(UserFollowInVO userFollowInVO){
        try {
            int effectedNum = 0;

            effectedNum = userDao.deleteFollowing(userFollowInVO);

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
}
