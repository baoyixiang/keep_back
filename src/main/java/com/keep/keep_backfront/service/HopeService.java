package com.keep.keep_backfront.service;

import com.keep.keep_backfront.dao.HopeDao;
import com.keep.keep_backfront.entity.Hope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Service
public class HopeService {

    @Autowired
    private HopeDao hopeDao;

    /**
     * 在树洞中发布一条心愿
     */
    ResponseEntity publishHope(){
        Hope hope = new Hope();

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


    /**
     *  获取树洞中的树洞列表
     */
    public List<Hope> getHopeList(){
        List<Hope> hopeList = null;
        try{
            hopeList = hopeDao.allHopesList();

        }catch (Exception e){
            e.printStackTrace();
        }
        return hopeList;
    }

    /**
     * 获取一个用户发布的树洞
     */
    List<Hope> getHopeListByUser(Integer userId){
        return hopeDao.singleHopeList(userId);
    }

    /**
     * 用户可以随机获取一条其他用户发布的树洞心愿
     */
    public Hope getHopeRandom(){
        return hopeDao.oneRandHope();
    }

}
