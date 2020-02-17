package com.keep.keep_backfront.controller.front;

import com.keep.keep_backfront.entity.Custom;
import com.keep.keep_backfront.service.CustomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bao
 */
@Api("小程序端习惯相关Api")
@RestController
@RequestMapping("api/user/custom")
public class CustomController {

    @Autowired
    private CustomService customService;

    @ApiOperation("新增习惯")
    @PostMapping("insertCustom")
    public ResponseEntity insertCustom(String title, Integer userId) {

        return customService.insertCustom(title, userId);
    }

    @ApiOperation("获取某个用户创建的习惯列表")
    @PostMapping("getCustomListByUserId")
    public List<Custom> getListByUser(Integer userId) {
        return customService.getCustomByUser(userId);
    }


}
