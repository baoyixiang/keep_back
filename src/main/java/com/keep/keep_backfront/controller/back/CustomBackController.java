package com.keep.keep_backfront.controller.back;


import com.github.pagehelper.PageInfo;
import com.keep.keep_backfront.VO.inVO.custom.AddCustomInVO;
import com.keep.keep_backfront.VO.inVO.custom.CustomListInVO;
import com.keep.keep_backfront.entity.Custom;
import com.keep.keep_backfront.service.CustomService;
import com.keep.keep_backfront.util.PageBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api("管理员端习惯api")
@RestController
@RequestMapping("backApi/custom")
public class CustomBackController {

    @Autowired
    private CustomService customService;

    @ApiOperation("管理员新增默认习惯")
    @PostMapping("add")
    public ResponseEntity add(@RequestBody AddCustomInVO inVO) {
        inVO.checkParam();
        return customService.adminInsert(inVO);
    }


    @ApiOperation("管理员删除默认习惯")
    @DeleteMapping("delete/{customId}")
    public ResponseEntity delete(@PathVariable("customId") Integer customId) {
        return customService.deleteCustom(customId);
    }

    @ApiOperation("管理员获取习惯")
    @PostMapping("list")
    public PageInfo<Custom> list(@RequestBody CustomListInVO request) {
        return customService.getCustomList(request);
    }
}
