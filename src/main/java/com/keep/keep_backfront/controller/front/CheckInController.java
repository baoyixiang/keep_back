package com.keep.keep_backfront.controller.front;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.keep.keep_backfront.VO.inVO.checkin.CheckInRequest;
import com.keep.keep_backfront.service.CheckInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Api("小程序端打卡模块api")
@RestController
@RequestMapping("api/user/checkin")
public class CheckInController {

    public CheckInService checkInService;

    @Autowired
    public CheckInController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @ApiOperation("习惯打卡")
    @PostMapping("checkIn")
    public ResponseEntity checkIn(@RequestBody CheckInRequest request){
        return checkInService.insertCheckIn(request);
    }

}
