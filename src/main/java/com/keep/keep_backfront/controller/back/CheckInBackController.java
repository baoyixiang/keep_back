package com.keep.keep_backfront.controller.back;

import com.keep.keep_backfront.VO.inVO.checkin.CheckInsInVO;
import com.keep.keep_backfront.VO.outVO.checkIn.AllCheckInOutVO;
import com.keep.keep_backfront.VO.outVO.checkIn.CheckInOutVO;
import com.keep.keep_backfront.service.CheckInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api("管理员打卡模块api")
@RestController
@RequestMapping("api/checkin")
public class CheckInBackController {

    private CheckInService checkInService;

    @Autowired
    public CheckInBackController(CheckInService checkInService) {
        this.checkInService = checkInService;
    }

    @ApiOperation("习惯所有打卡记录")
    @PostMapping("checkInList")
    public AllCheckInOutVO getCheckInListByCustom(@RequestBody CheckInsInVO checkInsInVO){
        return checkInService.getCheckIns(checkInsInVO);
    }

    @ApiOperation("删除打卡记录")
    @DeleteMapping("deleteCheckIn/{checkInId}")
    public ResponseEntity delCheckInById(@PathVariable Integer checkInId){
        return checkInService.delCheckIn(checkInId);
    }
}
