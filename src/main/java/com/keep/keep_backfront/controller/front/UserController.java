package com.keep.keep_backfront.controller.front;

import com.alibaba.fastjson.JSON;
import com.keep.keep_backfront.VO.inVO.user.UserFollowInVO;
import com.keep.keep_backfront.VO.outVO.user.UserHomeOutVO;
import com.keep.keep_backfront.VO.inVO.user.UserLoginVO;
import com.keep.keep_backfront.VO.outVO.user.UserLoginOutVO;
import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api("小程序端用户相关api")
@RestController
@RequestMapping("api/user")
public class UserController {


    private UserService userService;
    private UserDao userDao;

    @Autowired
    public UserController(UserService userService,UserDao userDao) {
        this.userService = userService;
        this.userDao = userDao;
    }

    @ApiOperation("用户登录")
    @PostMapping("login")
    public User userLogin(@RequestBody UserLoginVO userLoginVO){
        return userService.login(userLoginVO);
    }

    @ApiOperation("关注用户")
    @PostMapping("follow")
    public ResponseEntity userFollow(@RequestBody UserFollowInVO userFollowInVO){
        userFollowInVO.checkParam();
        return userService.attentionUser(userFollowInVO);
    }

    @ApiOperation("取消关注")
    @PostMapping("cancelFollow")
    public ResponseEntity cancelFollow(@RequestBody UserFollowInVO userFollowInVO){
        userFollowInVO.checkParam();
        return userService.cancelFollow(userFollowInVO);
    }

    @ApiOperation("我的关注")
    @GetMapping("following/{userId}")
    List<User> followingList(@PathVariable Integer userId){
        return userDao.followingUser(userId);
    }

    @ApiOperation("我的粉丝")
    @GetMapping("fans/{userId}")
    List<User> fansList(@PathVariable Integer userId){
        return userDao.followsOfMe(userId);
    };

    //获取用户推荐列表（待完成）
    @ApiOperation("获取推荐用户列表")
    @GetMapping("recommend_user_list")
    public List<User> getRecommendUser() {return userDao.getUserByPopularity();}

}
