package com.keep.keep_backfront.controller.back;

import com.keep.keep_backfront.dao.HopeDao;
import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api("后台用户api")
@RestController
@RequestMapping("backApi/user")
public class UserBackController {

    @Autowired
    private UserDao userDao;

    @ApiOperation("获取所有用户列表")
    @GetMapping("listUsers")
    public List<User> getAllUser() {
        return userDao.listUser();
    }

    @ApiOperation("设置推荐用户")
    @PostMapping("recommend")
    public void setRecommended(@RequestParam Integer userId){
        userDao.setRecommended(userId);
    }
}
