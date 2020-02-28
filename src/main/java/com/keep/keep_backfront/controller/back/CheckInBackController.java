package com.keep.keep_backfront.controller.back;

import com.keep.keep_backfront.VO.outVO.checkIn.CheckInOutVO;
import com.keep.keep_backfront.service.CheckInService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping("checkInList/{customId}")
    public List<CheckInOutVO> getCheckInListByCustom(@PathVariable Integer customId){
        return checkInService.getCheckIns(customId);
    }
}
