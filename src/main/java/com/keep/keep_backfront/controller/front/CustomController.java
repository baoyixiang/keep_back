package com.keep.keep_backfront.controller.front;

import com.github.pagehelper.PageInfo;
import com.keep.keep_backfront.VO.inVO.custom.*;
import com.keep.keep_backfront.VO.outVO.custom.CustomDetailOutVO;
import com.keep.keep_backfront.entity.Custom;
import com.keep.keep_backfront.service.CustomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("insert_custom")
    public ResponseEntity insertCustom(@RequestBody AddCustomInVO request) {
        request.checkParam();
        return customService.insertCustom(request.getTitle(), request.getUserId());
    }

    @ApiOperation("获取习惯列表")
    @PostMapping("list")
    public PageInfo<Custom> getCustomList(@RequestBody CustomListInVO request) {
        return customService.getCustomList(request);
    }

    @ApiOperation("用户加入一个习惯")
    @PostMapping("join_custom")
    public ResponseEntity joinCustom(@RequestBody JoinCustomInVO request) {
        return customService.joinCustom(request);
    }

    @ApiOperation("用户修改加入习惯的信息")
    @PostMapping("update_join_custom")
    public ResponseEntity updateJoinCustom(@RequestBody JoinCustomInVO request) {
        return customService.joinCustom(request);
    }

    @ApiOperation("用户获取自己加入习惯的详细信息")
    @PostMapping("custom_detail")
    public CustomDetailOutVO getCustomDetails(@RequestBody UserCustomInVO request) {
        return customService.getCustomDetails(request);
    }

    @ApiOperation("获取推荐习惯列表")
    @PostMapping("recommend_custom_list")
    public List<List<Custom>> getRecommendCustom(@RequestBody recommendListInVo request) {
        return customService.getRecommendCustomList(request);
    }

}
