package com.keep.keep_backfront.controller.back;

import com.keep.keep_backfront.dao.HopeDao;
import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@Api("后台用户api")
@RestController
@RequestMapping("backapi/user")
public class UserBackController {

    private UserDao userDao;

    @ApiOperation("获取所有用户列表")
    @GetMapping("listUsers")
    public List<User> getAllUser() {
        return userDao.listUser();
    }
}
