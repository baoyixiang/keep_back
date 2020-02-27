package com.keep.keep_backfront.controller.front;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.keep.keep_backfront.VO.inVO.checkin.CheckInCommentInOV;
import com.keep.keep_backfront.VO.inVO.checkin.CheckInRequest;
import com.keep.keep_backfront.VO.inVO.checkin.LikeCheckInOV;
import com.keep.keep_backfront.VO.outVO.checkIn.CheckInDetail;
import com.keep.keep_backfront.service.CheckInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("打卡")
    @PostMapping("checkIn")
    public ResponseEntity checkIn(@RequestBody CheckInRequest request){
        return checkInService.insertCheckIn(request);
    }
    @ApiOperation("取消/删除打卡")
    @DeleteMapping("deleteCheckIn/{checkInId}")
    public ResponseEntity deleteCheckIn(@PathVariable Integer checkInId){
        return checkInService.deleteCheckIn(checkInId);
    }

    @ApiOperation("打卡评论")
    @PostMapping("checkInComment")
    public ResponseEntity checkInComment(@RequestBody CheckInCommentInOV request){
        return checkInService.insertCheckInComment(request);
    }

    @ApiOperation("打卡点赞")
    @PostMapping("LikeCheckIn")
    public ResponseEntity likeCheckIn(@RequestBody LikeCheckInOV request){
        return checkInService.insertLikeCheckIn(request);
    }

    @ApiOperation("打卡详情")
    @GetMapping("checkInDetail/{checkInId}")
    public CheckInDetail getCheckInDetail(@PathVariable Integer checkInId){
        return checkInService.getCheckInDetailById(checkInId);
    }

}
