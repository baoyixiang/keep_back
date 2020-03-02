package com.keep.keep_backfront.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.keep.keep_backfront.VO.inVO.checkin.*;
import com.keep.keep_backfront.VO.outVO.checkIn.AllCheckInOutVO;
import com.keep.keep_backfront.VO.outVO.checkIn.CheckInDetail;
import com.keep.keep_backfront.VO.outVO.checkIn.CheckInOutVO;
import com.keep.keep_backfront.VO.outVO.checkIn.CheckInStateOutVO;
import com.keep.keep_backfront.dao.CheckInDao;
import com.keep.keep_backfront.dao.CustomDao;
import com.keep.keep_backfront.dao.UserDao;
import com.keep.keep_backfront.entity.*;
import com.keep.keep_backfront.util.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CheckInService {

    private CheckInDao checkInDao;
    private UserDao userDao;

    @Autowired
    public CheckInService(CheckInDao checkInDao,UserDao userDao) {
        this.checkInDao = checkInDao;
        this.userDao = userDao;
    }

    @Autowired
    public CustomDao customDao;

    /**
     * 打卡
     */
    public CheckInStateOutVO insertCheckIn(CheckInRequest request){

        CheckInStateOutVO outVO = new CheckInStateOutVO();
        outVO.setMsg("打卡成功");

        CheckIn checkIn = new CheckIn();
        List<CheckIn> checkIns = checkInDao.getCheckInsByCustomAndUser(request.getCustomId(),request.getUserId());//获取用户对应习惯的所有打卡记录
        //判断用户今日是否已经在此习惯上打卡
        SimpleDateFormat s1 = new SimpleDateFormat("D");
        String daytime = s1.format(new Date());//今天是一年的第几天
        for (CheckIn check:checkIns) {
            String lastCheckTime = s1.format(check.getCheckInTime());
            if(daytime.equals(lastCheckTime)){//用户今日已打卡
                outVO.setOptSuccess(false);
                outVO.setMsg("打卡失败，今日已经打卡");
                return outVO;
            }
        }

        checkIn.setUserId(request.getUserId());
        checkIn.setCustomeId(request.getCustomId());
        checkIn.setCheckInTime(new Date());
        checkIn.setDays(checkIns.size()+1);//设置当前打卡的天数

        // 更新join custom表
        JoinCustom joinCustom = customDao.findJoinCustomByUserAndCustom(request.getUserId(), request.getCustomId());
        joinCustom.setCheckDaysCount(checkIns.size()+1);
        if (joinCustom.getTargetDays() <= joinCustom.getCheckDaysCount())
            joinCustom.setCompleted(true);

        try {
            customDao.updateJoinCustom(joinCustom);

            int effectedNum = checkInDao.insertCheckIn(checkIn);
            if (effectedNum != 1) {
                outVO.setOptSuccess(false);
                outVO.setMsg("打卡失败，插入打卡记录失败");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            outVO.setOptSuccess(false);
            outVO.setMsg("打卡失败");
        }
        return outVO;
    }

    /**
     * 取消打卡
     */
    public CheckInStateOutVO deleteCheckIn(CheckInRequest request){
        CheckInStateOutVO outVO = new CheckInStateOutVO();
        Integer checkInId = checkInDao.getCheckInIdByUserAndCustom(request.getUserId(),request.getCustomId()).getId();
        if(checkInId==0){
            outVO.setOptSuccess(false);
            outVO.setMsg("取消失败,当日未打卡");
        }

        Integer userId = checkInDao.getUserIdByCheckIn(checkInId);
        Integer customId = checkInDao.getCustomIdByCheckIn(checkInId);
        System.out.println(userId + "---" + customId);
        // 更新join custom表
        JoinCustom joinCustom = customDao.findJoinCustomByUserAndCustom(userId,customId);
        joinCustom.setCheckDaysCount(customDao.getCheckDaysCountByUserAndCustom(userId,customId)-1);

        try {
            customDao.updateJoinCustom(joinCustom);

            int effectedNum = checkInDao.deleteCheckIn(checkInId);
            if (effectedNum != 1) {
                outVO.setOptSuccess(false);
            }else {
                outVO.setOptSuccess(true);
                outVO.setMsg("取消成功");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            outVO.setOptSuccess(false);
        }
        return outVO;
    }

    /**
     * 记录心愿
     */
    public ResponseEntity checkInRecord(CheckInRecord record){
        CheckIn checkIn = checkInDao.getCheckInIdByUserAndCustom(record.getUserId(),record.getCustomId());
        checkIn.setWordContent(record.getWordContent());
        checkIn.setImages(JSONArray.parseArray(JSON.toJSONString(record.getImages())));
        checkIn.setVoice(record.getVoice());

        try {
            int effectedNum = checkInDao.updateCheckIn(checkIn);
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

    /**
     * 后台获取习惯所有打卡记录
     */
    public AllCheckInOutVO getCheckIns(CheckInsInVO checkInsInVO){

        PageHelper.startPage(checkInsInVO.getPageNo(), checkInsInVO.getPageSize());

        AllCheckInOutVO allCheckInOutVO = new AllCheckInOutVO();
        List<CheckInOutVO> checkInOutVOS = new ArrayList<CheckInOutVO>();

        try{
            List<CheckIn> checkIns = checkInDao.getCheckInByCustom(checkInsInVO.getCustomId());
            for (CheckIn checkIn:checkIns) {
                CheckInOutVO checkInOutVO = new CheckInOutVO();
                checkInOutVO.setCheckInId(checkIn.getId());//打卡id
                checkInOutVO.setUserName(userDao.getUserById(checkIn.getUserId()).getName());//名字
                checkInOutVO.setCheckInTime(checkIn.getCheckInTime());//打卡时间
                checkInOutVO.setWordContent(checkIn.getWordContent());//打卡内容
                checkInOutVO.setLikeCount(checkInDao.getLikeCountByCheckIn(checkIn.getId()));//打卡点赞数
                checkInOutVO.setCommentCount(checkInDao.getCheckInCommentsByCheckInId(checkIn.getId()).size());//打卡评论数

                checkInOutVOS.add(checkInOutVO);
            }

            allCheckInOutVO.setCheckInOutVOS(checkInOutVOS);//打卡的信息
            allCheckInOutVO.setCheckInToday(checkInDao.getTodayCheckInByCustom(checkInsInVO.getCustomId()));//习惯今日打卡数
            allCheckInOutVO.setJoinCount(customDao.getJoinCountByCustom(checkInsInVO.getCustomId()));//习惯总打卡数

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("checkInOutVOS信息获取失败");
        }

        return allCheckInOutVO;
    }

    // 用户端获取一个习惯的所有的打卡记录，包括评论和点赞
    public PageBean<CheckInDetail> userGetCheckIns(CheckInsInVO inVO) {
        PageHelper.startPage(inVO.getPageNo(), inVO.getPageSize());
        List<CheckIn> checkIns = checkInDao.getCheckInsByCustomAndUser(inVO.getCustomId(), inVO.getUserId());
        PageInfo<CheckIn> info = new PageInfo<>(checkIns);

        PageBean<CheckInDetail> results = new PageBean<>(inVO.getPageNo(), inVO.getPageSize(), info.getTotal());
        List<CheckInDetail> checkInDetails = new ArrayList<>();
        checkIns.forEach(it -> {
            checkInDetails.add(getCheckInDetailById(it.getId()));
        });
        results.setItems(checkInDetails);
        return results;
    }
}
