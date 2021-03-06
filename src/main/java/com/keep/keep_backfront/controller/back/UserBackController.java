package com.keep.keep_backfront.controller.back;

import com.alibaba.fastjson.JSON;
import com.keep.keep_backfront.VO.inVO.user.AllUsersListInVO;
import com.keep.keep_backfront.VO.outVO.user.AllUsersListOutVO;
import com.keep.keep_backfront.dao.HopeDao;
import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api("后台用户api")
@RestController
@RequestMapping("backApi/user")
public class UserBackController {

    private UserDao userDao;
    private UserService userService;

    @Autowired
    public UserBackController(UserDao userDao,UserService userService){
        this.userDao = userDao;
        this.userService = userService;
    }


    @ApiOperation("获取所有用户列表")
    @PostMapping("listUsers")
    public List<AllUsersListOutVO> getAllUser(@RequestBody AllUsersListInVO request) {
        return userService.getAllUsersList(request);
    }

    @ApiOperation("设置推荐用户")
    @PostMapping("recommend")
    public ResponseEntity setRecommended(@RequestBody String str){
        Integer userId = Integer.parseInt(JSON.parseObject(str).get("userId").toString());
        try {
            int effectedNum = userDao.setRecommended(userId);;
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
