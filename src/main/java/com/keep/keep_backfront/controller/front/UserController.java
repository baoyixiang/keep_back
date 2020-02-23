package com.keep.keep_backfront.controller.front;

import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user/user_list")
public class UserController {

    @Autowired
    private UserDao userDao;

    @ApiOperation("获取用户列表")
    @GetMapping("listUsers")
    public List<User> getAllUser() {
        return userDao.listUser();
    }

    //获取用户推荐列表（待完成）
    @ApiOperation("获取推荐用户列表")
    @GetMapping("recommend_user_list")
    public List<User> getRecommendUser() {return userDao.getUserByPopularity();}
}
