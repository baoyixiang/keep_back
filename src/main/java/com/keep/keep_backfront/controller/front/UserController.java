package com.keep.keep_backfront.controller.front;

import com.keep.keep_backfront.VO.outVO.user.UserHomeVO;
import com.keep.keep_backfront.VO.inVO.user.UserLoginVO;
import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("小程序端用户相关api")
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    @ApiOperation("用户登录")
    @PostMapping("login")
    public ResponseEntity userLogin(@RequestBody UserLoginVO userLoginVO){
//        System.out.println(userLoginVO.toString());
        return userService.login(userLoginVO);
    }

    @ApiOperation("用户主页")
    @PostMapping("userhome")
    public UserHomeVO getUserHome(@RequestParam Integer userId){
        return userService.userHome(userId);
    }

    @ApiOperation("关注用户")
    @PostMapping("follow")
    public void userFollow(@RequestParam Integer userId,@RequestParam Integer followesUserId){
        userService.attentionUser(userId,followesUserId);
    }

    @ApiOperation("我的关注")
    @PostMapping("following")
    List<User> followingList(@RequestParam Integer myId){
        return userDao.followingUser(myId);
    }

    @ApiOperation("我的粉丝")
    @PostMapping("fans")
    List<User> fansList(@RequestParam Integer myId){
        return userDao.followsOfMe(myId);
    };

}
