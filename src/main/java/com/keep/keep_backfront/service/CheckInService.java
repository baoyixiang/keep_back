package com.keep.keep_backfront.service;

import com.keep.keep_backfront.VO.inVO.checkin.CheckInCommentInOV;
import com.keep.keep_backfront.VO.inVO.checkin.CheckInRequest;
import com.keep.keep_backfront.VO.inVO.checkin.LikeCheckInOV;
import com.keep.keep_backfront.VO.outVO.checkIn.CheckInDetail;
import com.keep.keep_backfront.dao.CheckInDao;
import com.keep.keep_backfront.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class CheckInService {

    public CheckInDao checkInDao;

    @Autowired
    public CheckInService(CheckInDao checkInDao) {
        this.checkInDao = checkInDao;
    }

    /**
     * 打卡
     */
    public ResponseEntity insertCheckIn(CheckInRequest request){
        CheckIn checkIn = new CheckIn();
        List<CheckIn> checkIns = checkInDao.getCheckInsByCustomeId(request.getCustomId(),request.getUserId());//获取用户对应习惯的所有打卡记录
        checkIn.setUserId(request.getUserId());
        checkIn.setCustomeId(request.getCustomId());
        checkIn.setCheckInTime(new Date());
        checkIn.setWordContent(request.getWordContent());
        checkIn.setImages(request.getImages());
        checkIn.setVoice(request.getVoice());
        checkIn.setDays(checkIns.size()+1);//设置当前打卡的天数
        try {
            int effectedNum = checkInDao.insertCheckIn(checkIn);
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
     * 打卡评论
     */
    public ResponseEntity insertCheckInComment(CheckInCommentInOV checkInCommentInOV){
        CheckInComments checkInComments = new CheckInComments();

        checkInComments.setUserId(checkInCommentInOV.getUserId());
        checkInComments.setCheckInId(checkInCommentInOV.getCheckInId());
        checkInComments.setCommentContent(checkInCommentInOV.getCommentContent());
        checkInComments.setCommentTime(new Date());
        checkInComments.setReplyTo(checkInCommentInOV.getReplyTo());

        try {
            int effectedNum = checkInDao.insertCheckInComment(checkInComments);//插入打卡评论
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
     * 打卡点赞
     */
    public ResponseEntity insertLikeCheckIn(LikeCheckInOV likeCheckInOV){
        UserLikeCheckIn userLikeCheckIn = new UserLikeCheckIn();

        userLikeCheckIn.setUserId(likeCheckInOV.getUserId());
        userLikeCheckIn.setCheckInId(likeCheckInOV.getCheckInId());
        userLikeCheckIn.setLikeTime(new Date());

        try {
            int effectedNum = checkInDao.insertUserLikeCheckIn(userLikeCheckIn);//插入打卡点赞
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
     * 取消/删除打卡记录
     */
    public ResponseEntity deleteCheckIn(Integer checkInId){
        try {
            int effectedNum = checkInDao.deleteCheckIn(checkInId);
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
     * 获取打卡详情
     */
    public CheckInDetail getCheckInDetailById(Integer checkInId){
        CheckInDetail checkInDetail = new CheckInDetail();

        try{
            checkInDetail.setCheckIn(checkInDao.getCheckInByCheckInId(checkInId));//打卡记录
            checkInDetail.setCheckInComments(checkInDao.getCheckInCommentsByCheckInId(checkInId));//打卡评论
            checkInDetail.setLikeUsers(checkInDao.getLikeUsersByCheckInId(checkInId));//打卡的点赞用户
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("checkInDetail信息获取失败");
        }

        return checkInDetail;
    }
}
