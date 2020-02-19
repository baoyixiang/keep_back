package com.keep.keep_backfront.controller.front;

import com.keep.keep_backfront.entity.Hope;
import com.keep.keep_backfront.service.HopeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("小程序端树洞相关api")
@RestController
@RequestMapping("api/user/hope")
public class HopeController {


    @Autowired
    private HopeService hopeService;

    @ApiOperation("发布一条树洞心愿")
    @PostMapping("newHope")
    public ResponseEntity publishHope(@RequestBody Hope hope){
        return hopeService.publishHope(hope);
    }

    @ApiOperation("随机获取一条树洞心愿")
    @GetMapping("randomHope")
    public Hope getHopeRandom(){
        return hopeService.getHopeRandom();
    }

    @ApiOperation("获取树洞中的树洞列表")
    @GetMapping("allHopes")
    public List<Hope> getHopesList(){
        return hopeService.getHopeList();
    }

}

