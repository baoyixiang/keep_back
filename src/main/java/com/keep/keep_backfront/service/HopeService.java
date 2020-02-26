package com.keep.keep_backfront.service;

import com.github.pagehelper.PageHelper;
import com.keep.keep_backfront.VO.inVO.hope.*;
import com.keep.keep_backfront.VO.outVO.hope.HopeListOutVO;
import com.keep.keep_backfront.dao.HopeDao;
import com.keep.keep_backfront.entity.Hope;
import com.keep.keep_backfront.entity.HopeComment;
import com.keep.keep_backfront.entity.HopeDetail;
import com.keep.keep_backfront.entity.UserLikeHope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HopeService {

    private HopeDao hopeDao;

    @Autowired
    public HopeService(HopeDao hopeDao){
        this.hopeDao = hopeDao;
    }

    /**
     * 在树洞中发布一条心愿
     */
    public ResponseEntity publishHope(PublishHopeInVO request){

        Hope hope = new Hope();
        hope.setWordContent(request.getWordContent());
        hope.setCreateUserId(request.getCreateUserId());
        hope.setCreateTime(request.getCreateTime());
        hope.setAnonymous(request.getIsAnonymous());
        hope.setSeeSelf(request.getIsSeeSelf());
        hope.setImages(request.getImages());    //存入images
        hope.setLikeCount(0);
        hope.setCommentCount(0);

        try {
            int effectedNum = hopeDao.insertHope(hope);
            if (effectedNum == 1) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//    /**
//     *  用户删除自己的心愿(未完)
//     */
//    public ResponseEntity deleteUserOwnHope(HopeDeleteInVO hopeDeleteInVO){
//        try {
//            int effectedNum = 1;
//            if (effectedNum == 1) {
//                return ResponseEntity.status(HttpStatus.OK).build();
//            } else {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }


    /**
     *  获取树洞中的树洞心愿列表
     */
    public List<HopeListOutVO> getAllHopesList(AllHopeListInVO allHopeListInVO){
        PageHelper.startPage(allHopeListInVO.getPageNo(), allHopeListInVO.getPageSize());
        List<HopeListOutVO> hopeList ;
        try{
            hopeList = hopeDao.allHopesList();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("allHopeList信息获取失败");
        }
        return hopeList;
    }

    /**
     * 获取一个用户发布的树洞心愿
     */
    public List<Hope> getHopeListByUser(HopeListInVO hopeListInVO){
        PageHelper.startPage(hopeListInVO.getPageNo(), hopeListInVO.getPageSize());
        List<Hope> hopeList ;
        try{
            hopeList = hopeDao.HopeListByUser(hopeListInVO.getUserId());
            System.out.println(hopeList);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("hopeList信息获取失败");
        }
        return hopeList;
    }

    /**
     * 用户可以随机获取一条其他用户发布的树洞心愿
     */
    public Hope getHopeRandom(){
        try{
            return hopeDao.oneRandHope();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("hope信息获取失败");
        }
    }

    /**
     * 用户评论一条hope，产生一条评论记录
     */
    public ResponseEntity publishHopeComment(PublishHopeCommentsInVO publishHopeCommentsInVO){
        HopeComment hopeComment = new HopeComment();
        hopeComment.setUserId(publishHopeCommentsInVO.getUserId());
        hopeComment.setHopeId(publishHopeCommentsInVO.getHopeId());
        hopeComment.setCommentContent(publishHopeCommentsInVO.getCommentContent());
        hopeComment.setCommentTime(new Date());
        hopeComment.setReplyTo(publishHopeCommentsInVO.getReplyTo());

        try {
            int effectedNum = hopeDao.insertHopeComment(hopeComment);//插入心愿评论
            effectedNum = hopeDao.updateHopeCommentCount(hopeComment.getHopeId());//对应心愿的评论数+1
            if (effectedNum == 1) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    /**
     * 用户删除一条hope评论
     */
    public ResponseEntity deleteHopeComment(HopeComment hopeComment){
        try {
            int effectedNum = hopeDao.deleteHopeComment(hopeComment.getId());//删除心愿评论
            hopeDao.updateHopeCommentCount0(hopeComment.getHopeId());//对应心愿的评论数-1
            if (effectedNum == 1) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     *用户点赞一条hope，产生一条userLikeHope记录
     */
    public ResponseEntity likeHope(AddLikeHopeVO addLikeHopeVO){
        UserLikeHope userLikeHope = new UserLikeHope();

        userLikeHope.setUserId(addLikeHopeVO.getUserId());
        userLikeHope.setHopeId(addLikeHopeVO.getHopeId());
        userLikeHope.setLikeState(addLikeHopeVO.getLikeState());
        userLikeHope.setLikeTime(new Date());

        try {
            int effectedNum = 0;
            if(userLikeHope.getLikeState()==1){//点赞，对应hope点赞数+1
               effectedNum = hopeDao.insertUserLikeHope(userLikeHope);
               effectedNum = hopeDao.updateLikeCount(userLikeHope);
            }
            else {//取消点赞，对应hope点赞数-1
                effectedNum = hopeDao.cancelLike(addLikeHopeVO);
            }

            if (effectedNum==1) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * 获取详细心愿hope_detail
     */
    public HopeDetail hopeDetail(Integer hopeId){
        HopeDetail hopeDetail = new HopeDetail();
        try{
            Hope hope = hopeDao.getHopeByHopeId(hopeId);

            hopeDetail.setWordContent(hope.getWordContent());
            hopeDetail.setLikeCount(hope.getLikeCount());
            hopeDetail.setCommentCount(hope.getCommentCount());
            hopeDetail.setHopeId(hope.getId());
            hopeDetail.setHopecomments(hopeDao.hopeComments(hopeId));
            hopeDetail.setImages(hope.getImages());
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("hopeDetail信息获取失败");
        }
        return hopeDetail;
    }

    /**
     *  删除一条心愿
     */
    public ResponseEntity hopeDelete(Integer hopeId){
        try {
            int effectedNum = hopeDao.deleteHope(hopeId);//删除心愿
            hopeDao.deleteHopeCommentsByHopeId(hopeId);//删除对应心愿的所有评论
            if (effectedNum == 1) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
