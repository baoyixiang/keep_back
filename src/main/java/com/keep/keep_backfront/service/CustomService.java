package com.keep.keep_backfront.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.keep.keep_backfront.VO.inVO.custom.*;
import com.keep.keep_backfront.VO.outVO.custom.CustomDetailOutVO;
import com.keep.keep_backfront.dao.CheckInDao;
import com.keep.keep_backfront.dao.CustomDao;
import com.keep.keep_backfront.entity.Custom;
import com.keep.keep_backfront.entity.JoinCustom;
import com.keep.keep_backfront.handler.exception.ParameterErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * @author bao
 */
@Service
public class CustomService {

    @Autowired
    private CustomDao customDao;

    @Autowired
    private CheckInDao checkInDao;

    public ResponseEntity userInsert(AddCustomInVO request) {
        return insertCustom(request, false);
    }

    public ResponseEntity adminInsert(AddCustomInVO request) {
        return insertCustom(request, true);
    }

    /**
     * 插入一条习惯信息
     */
    public ResponseEntity insertCustom(AddCustomInVO request, Boolean isDefault) {
        Custom custom = new Custom();
        custom.setTitle(request.getTitle());
        custom.setDefault(isDefault);
        custom.setCreateTime(new Date());
        custom.setCreateUserId(request.getUserId());
        custom.setLogo(request.getLogo());
        custom.setTags(JSONArray.parseArray(JSON.toJSONString(request.getTags())));
        try {
            int effectedNum = customDao.insertCustom(custom);
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
     * 获取习惯列表
     */
    public PageInfo<Custom> getCustomList(CustomListInVO inVO) {
        PageHelper.startPage(inVO.getPageNo(), inVO.getPageSize());
        try {
            List<Custom> list = customDao.customList(inVO.getUserId(), inVO.getTitle(), inVO.getIsDefault());
            return new PageInfo<>(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("信息获取失败");
        }
    }

    /**
     * 用户加入一个习惯
     */
    public ResponseEntity joinCustom(JoinCustomInVO inVO) {
        JoinCustom joinCustom = new JoinCustom();
        joinCustom.setUserId(inVO.getUserId());
        joinCustom.setCustomId(inVO.getCustomId());
        joinCustom.setJoinTime(new Date());
        joinCustom.setPublic(inVO.getIsPublic());
        joinCustom.setTargetDays(inVO.getTargetDay());
        joinCustom.setCompleted(false);
        joinCustom.setBeansCount(inVO.getBeansCount());
        joinCustom.setCheckDaysCount(0);

        try {
            System.out.println("customId:" + inVO.getCustomId());
            customDao.joinCustom(joinCustom);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity updateCustom(JoinCustomInVO inVO) {
        JoinCustom joinCustom = customDao.findJoinCustomById(inVO.getId());
        BeanUtils.copyProperties(inVO, joinCustom);
        try {
            customDao.updateJoinCustom(joinCustom);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity deleteCustom(Integer customId) {
        if (customDao.findCustomById(customId) == null) {
            throw new ParameterErrorException("习惯记录不存在");
        }

        try {
            customDao.deleteCustom(customId);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 获取习惯的详细信息，主要需要包括所有的打卡信息
    public CustomDetailOutVO getCustomDetails(UserCustomInVO inVO) {
        CustomDetailOutVO outVO = new CustomDetailOutVO();
        outVO.setJoinCustom(customDao.findJoinCustomByUserAndCustom(inVO.getUserId(), inVO.getCustomId()));
        outVO.setCheckInList(checkInDao.findCheckinByUserAndCustom(inVO.getUserId(), inVO.getCustomId()));
        return outVO;
    }

    //获取推荐习惯类型的列表
    public List<Custom> getRecommendCustomList(RecommendListInVO request) {
        List<Custom> recommendCustomList = customDao.findRecommendCustomByTag(request.getTag());
        return recommendCustomList;
    }
}
