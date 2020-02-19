package com.keep.keep_backfront.controller.front;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.keep.keep_backfront.VO.inVO.custom.AddCustomInVO;
import com.keep.keep_backfront.VO.inVO.custom.CustomListInVO;
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
    @PostMapping("insertCustom")
    public ResponseEntity insertCustom(@RequestBody AddCustomInVO request) {
        request.checkParam();
        return customService.insertCustom(request.getTitle(), request.getUserId());
    }

    @ApiOperation("获取习惯列表")
    @PostMapping("list")
    public PageInfo<Custom> getCustomList(@RequestBody CustomListInVO request) {
        return customService.getCustomList(request);
    }
}
