package com.keep.keep_backfront.controller.back;

import com.keep.keep_backfront.dao.HopeDao;
import com.keep.keep_backfront.entity.Hope;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Api("后台心愿api")
@RestController
@RequestMapping("backapi/hope")
public class HopeBackController {

    @Autowired
    private HopeDao hopeDao;

    @ApiOperation("删除心愿")
    @PostMapping("delHope")
    public void deleteHopeById(@RequestParam Integer hopeId){
        hopeDao.deleteHope(hopeId);//删除心愿
        hopeDao.deleteHopeCommentByhopeId(hopeId);//删除对应心愿的所有评论
    }

    @ApiOperation("心愿列表")
    @GetMapping("hopesList")
    List<Hope> hopesList(){
        return hopeDao.allHopesList();
    }

}
