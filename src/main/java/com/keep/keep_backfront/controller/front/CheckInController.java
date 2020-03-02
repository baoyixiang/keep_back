package com.keep.keep_backfront.controller.front;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.keep.keep_backfront.VO.inVO.checkin.*;
import com.keep.keep_backfront.VO.inVO.custom.UserCustomInVO;
import com.keep.keep_backfront.VO.outVO.checkIn.CheckInDetail;
import com.keep.keep_backfront.VO.outVO.checkIn.CheckInStateOutVO;
import com.keep.keep_backfront.dao.CheckInDao;
import com.keep.keep_backfront.entity.CheckIn;
import com.keep.keep_backfront.service.CheckInService;
import com.keep.keep_backfront.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api("小程序端打卡模块api")
@RestController
@RequestMapping("api/user/checkin")
public class CheckInController {

    public CheckInService checkInService;
    public CheckInDao checkInDao;

    @Autowired
    public CheckInController(CheckInService checkInService,CheckInDao checkInDao) {
        this.checkInService = checkInService;
        this.checkInDao = checkInDao;
    }

    @ApiOperation("打卡")
    @PostMapping("checkIn")
    public CheckInStateOutVO checkIn(@RequestBody CheckInRequest request){
        return checkInService.insertCheckIn(request);
    }
    @ApiOperation("取消打卡")
    @DeleteMapping("deleteCheckIn")
    public CheckInStateOutVO deleteCheckIn(@RequestBody CheckInRequest request){
        return checkInService.deleteCheckIn(request);
    }
    @ApiOperation("记录心情")
    @PostMapping("checkInRecord")
    public ResponseEntity recordCheckIn(@RequestBody CheckInRecord record){
        return checkInService.checkInRecord(record);
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

    @ApiOperation("我的打卡记录")
    @GetMapping("myCheckInList/{userId}")
    public List<CheckIn> getMyCheckInList(@PathVariable Integer userId){
        return checkInDao.getCheckInByUser(userId);
    }

    @ApiOperation("习惯的打卡记录")
    @PostMapping("CustomCheckIns")
    public PageBean<CheckInDetail> getCustomCheckInList(@RequestBody CheckInsInVO request){
        return checkInService.userGetCheckIns(request);
    };

}
