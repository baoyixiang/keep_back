package com.keep.keep_backfront.controller.back;

import com.alibaba.fastjson.JSON;
import com.keep.keep_backfront.VO.inVO.hope.AllHopeListInVO;
import com.keep.keep_backfront.VO.inVO.hope.HopeDeleteInVO;
import com.keep.keep_backfront.VO.inVO.hope.HopeListInVO;
import com.keep.keep_backfront.VO.outVO.hope.HopeListOutVO;
import com.keep.keep_backfront.dao.HopeDao;
import com.keep.keep_backfront.entity.Hope;
import com.keep.keep_backfront.service.HopeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api("后台心愿api")
@RestController
@RequestMapping("backApi/hope")
public class HopeBackController {

    private HopeService hopeService;

    @Autowired
    public HopeBackController(HopeService hopeService){
        this.hopeService = hopeService;
    }

    @ApiOperation("删除心愿")
    @PostMapping("delHope")
    public ResponseEntity deleteHopeById(@RequestBody String str){//接受json转为string
        Integer hopeId = Integer.parseInt(JSON.parseObject(str).get("hopeId").toString());//取出hopeId并转换
        return hopeService.hopeDelete(hopeId);
    }

    @ApiOperation("心愿列表")
    @PostMapping("allHopesList")
    List<HopeListOutVO> hopesList(@RequestBody AllHopeListInVO allHopeListInVO){
        return hopeService.getAllHopesList(allHopeListInVO);
    }

}
