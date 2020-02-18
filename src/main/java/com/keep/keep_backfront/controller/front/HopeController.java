package com.keep.keep_backfront.controller.front;

import com.keep.keep_backfront.entity.Hope;
import com.keep.keep_backfront.service.HopeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Api("小程序端树洞相关api")
@RequestMapping("api/user/hope")
public class HopeController {

    @Autowired
    private HopeService hopeService;

    @ApiOperation("获取所有心愿")
    @GetMapping("allHopes")
    public List<Hope> getHopesList(){
        return hopeService.getHopeList();
    }

}
