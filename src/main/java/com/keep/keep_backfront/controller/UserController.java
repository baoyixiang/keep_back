package com.keep.keep_backfront.controller;

import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("listUsers")
    public List<User> getAllUser() {
        return userDao.listUser();
    }
}
