package com.keep.keep_backfront.controller.front;

import com.github.pagehelper.PageInfo;
import com.keep.keep_backfront.VO.inVO.custom.*;
import com.keep.keep_backfront.VO.outVO.custom.CustomDetailOutVO;
import com.keep.keep_backfront.VO.outVO.custom.CustomListOutVO;
import com.keep.keep_backfront.VO.outVO.custom.RecommendCustomOutVO;
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
@CrossOrigin
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
        return customService.userInsert(request);
    }

    @ApiOperation("用户获取加入的习惯列表")
    @PostMapping("join_list")
    public PageInfo<CustomListOutVO> getJoinCustomList(@RequestBody CustomListInVO request) {
        return customService.getUserCustomList(request, false);
    }

    @ApiOperation("用户获取自己创建的习惯的列表")
    @PostMapping("create_list")
    public PageInfo<CustomListOutVO> getCustomList(@RequestBody CustomListInVO request) {
        return customService.getUserCustomList(request, true);
    }

    @ApiOperation("用户加入一个习惯")
    @PostMapping("join_custom")
    public ResponseEntity joinCustom(@RequestBody JoinCustomInVO request) {
        return customService.joinCustom(request);
    }

    @ApiOperation("用户修改加入习惯的信息")
    @PostMapping("updata_join_custom")
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
    public List<RecommendCustomOutVO> getRecommendCustom(@RequestBody RecommendListInVO request) {
        return customService.getRecommendCustomList(request);
    }
}
