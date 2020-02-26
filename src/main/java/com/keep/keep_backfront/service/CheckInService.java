package com.keep.keep_backfront.service;

import com.keep.keep_backfront.VO.inVO.checkin.CheckInRequest;
import com.keep.keep_backfront.dao.CheckInDao;
import com.keep.keep_backfront.entity.CheckIn;
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
        List<CheckIn> checkIns = checkInDao.getCheckInsByCustomeId(request.getCustomId());//获取对应习惯的所有打卡记录
        checkIn.setUserId(request.getUserId());
        checkIn.setCustomeId(request.getCustomId());
        checkIn.setCheckInTime(new Date());
        checkIn.setWordContent(request.getWordContent());
        checkIn.setImages(request.getImages());
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
}
