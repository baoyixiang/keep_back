package com.keep.keep_backfront.controller.front;

import com.keep.keep_backfront.VO.inVO.hope.*;
import com.keep.keep_backfront.VO.outVO.hope.HopeListOutVO;
import com.keep.keep_backfront.entity.Hope;
import com.keep.keep_backfront.entity.HopeComment;
import com.keep.keep_backfront.entity.HopeDetail;
import com.keep.keep_backfront.service.HopeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("小程序端树洞相关api")
@RestController
@RequestMapping("api/user/hope")
public class HopeController {

    private HopeService hopeService;

    @Autowired
    public HopeController(HopeService hopeService){
        this.hopeService = hopeService;
    }

    @ApiOperation("发布一条树洞心愿")
    @PostMapping("newHope")
    public ResponseEntity publishHope(@RequestBody PublishHopeInVO request){
        request.checkParam();
        return hopeService.publishHope(request);
    }

    @ApiOperation("用户删除自己的心愿")
    @PostMapping("delMyHope")
    public ResponseEntity deleteOwnerHope(@RequestBody HopeDeleteInVO hopeDeleteInVO){
        return hopeService.hopeDelete(hopeDeleteInVO.getHopeId());
    }

    @ApiOperation("随机获取一条树洞心愿")
    @GetMapping("randomHope")
    public Hope getHopeRandom(){
        return hopeService.getHopeRandom();
    }

    @ApiOperation("获取树洞中的所有树洞心愿列表")
    @PostMapping("allHopes")
    public List<HopeListOutVO> getAllHopesList(@RequestBody AllHopeListInVO request){
        return hopeService.getAllHopesList(request);
    }

    @ApiOperation("获取指定用户的树洞心愿列表")
    @PostMapping("hopeList")
    public List<Hope> getHopesListByUser(@RequestBody HopeListInVO request){
        return hopeService.getHopeListByUser(request);
    }

    @ApiOperation("发布一条树洞心愿评论")
    @PostMapping("newHopeComment")
    public ResponseEntity PublishHopeComment(@RequestBody PublishHopeCommentsInVO request){
        request.checkParam();
        return hopeService.publishHopeComment(request);
    }
    @ApiOperation("删除一条树洞心愿评论")
    @PostMapping("deleteMyHopeComment")
    public ResponseEntity DeleteHopeComment(@RequestBody HopeComment hopeComment){
        return hopeService.deleteHopeComment(hopeComment);
    }

    @ApiOperation("用户点赞(取消点赞)一条树洞心愿（likeState点赞为1，取消点赞为0）")
    @PostMapping("likeHope")
    public ResponseEntity AddLikeHope(@RequestBody AddLikeHopeVO request){
        request.checkParam();
        return hopeService.likeHope(request);
    }

    @ApiOperation("获取心愿详情hopeDetail")
    @PostMapping("hopeDetail")
    public HopeDetail getHopeDetail(@RequestBody Integer hopeId){
        return hopeService.hopeDetail(hopeId);
    }

}

