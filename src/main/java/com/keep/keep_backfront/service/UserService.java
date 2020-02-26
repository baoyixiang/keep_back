package com.keep.keep_backfront.service;

import com.github.pagehelper.PageHelper;
import com.keep.keep_backfront.VO.inVO.hope.AllHopeListInVO;
import com.keep.keep_backfront.VO.inVO.user.AllUsersListInVO;
import com.keep.keep_backfront.VO.inVO.user.UserFollowInVO;
import com.keep.keep_backfront.VO.outVO.hope.HopeListOutVO;
import com.keep.keep_backfront.VO.outVO.user.UserHomeOutVO;
import com.alibaba.fastjson.JSONArray;
import com.keep.keep_backfront.VO.outVO.user.*;
import com.keep.keep_backfront.VO.inVO.user.UserLoginVO;
import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.entity.UserAttention;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserService {

    private UserDao userDao;

    private WxLoginHelper wxLoginHelper;

    public UserService(UserDao userDao, WxLoginHelper wxLoginHelper) {
        this.userDao = userDao;
        this.wxLoginHelper = wxLoginHelper;
    }

    /**
     *  用户登录
     */
    public User login(UserLoginVO userLoginVO){
        User user = new User();
        System.out.println("1");
        WxResponse wxResponse = wxLoginHelper.loginWx(userLoginVO.getCode());
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
                user.setRecommended(false);
                userDao.newUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        user = userDao.getUserByWechatId(wxResponse.getOpenId());
        return user;
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

    /**
     *  获取所有用户列表(返回封装后的数据)
     */
    public List<AllUsersListOutVO> getAllUsersList(AllUsersListInVO request){
        PageHelper.startPage(request.getPageNo(), request.getPageSize());
        List<User> users = new ArrayList<>();
        List<AllUsersListOutVO> allUsersListOutVOS = new ArrayList<AllUsersListOutVO>();
        try{
            users = userDao.listUser(); //查询所有数据库用户
            for (User user:users) {
                AllUsersListOutVO allUsersListOutVO = new AllUsersListOutVO();
                allUsersListOutVO.setUser(user);
                allUsersListOutVO.setFollowedCount(userDao.followsOfMe(user.getId()).size());//被关注数
                allUsersListOutVO.setFollowingCount(userDao.followingUser(user.getId()).size());//关注数
                System.out.println(allUsersListOutVO.toString());
                allUsersListOutVOS.add(allUsersListOutVO);//加入返回的list
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("allUsersList信息获取失败");
        }
        return allUsersListOutVOS;
    }
}
