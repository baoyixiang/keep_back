package com.keep.keep_backfront.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.keep.keep_backfront.VO.inVO.custom.CustomListInVO;
import com.keep.keep_backfront.VO.inVO.custom.JoinCustomInVO;
import com.keep.keep_backfront.VO.inVO.custom.UserCustomInVO;
import com.keep.keep_backfront.VO.outVO.custom.CustomDetailOutVO;
import com.keep.keep_backfront.VO.outVO.custom.RecommendCustomOutVO;
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

    /**
     * 插入一条习惯信息
     */
    public ResponseEntity insertCustom(String title, Integer userId) {
        if (title == null) {
            throw new ParameterErrorException("习惯标题不能为空");
        }

        Custom custom = new Custom();
        custom.setTitle(title);
        custom.setDefault(false);
        custom.setCreateTime(new Date());
        custom.setCreateUserId(userId);
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
            List<Custom> list = customDao.customList(inVO.getUserId(), inVO.getTitle());
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

    // 获取习惯的详细信息，主要需要包括所有的打卡信息
    public CustomDetailOutVO getCustomDetails(UserCustomInVO inVO) {
        CustomDetailOutVO outVO = new CustomDetailOutVO();
        outVO.setJoinCustom(customDao.findJoinCustomByUserAndCustom(inVO.getUserId(), inVO.getCustomId()));
        outVO.setCheckInList(checkInDao.findCheckinByUserAndCustom(inVO.getUserId(), inVO.getCustomId()));
        return outVO;
    }

    //获取推荐习惯类型的列表
    public List<RecommendCustomOutVO> getRecommendCustomList(RecommendListInVO request) {
        List<RecommendCustomOutVO> recommendCustomList = new ArrayList<>();
        List<String> tagsList = request.getTagsList();
        for(String str : tagsList) {
            RecommendCustomOutVO recommendCustomOutVO = new RecommendCustomOutVO();
            recommendCustomOutVO.title = str;
            recommendCustomOutVO.customList = customDao.findRecommendCustomByTag(str);
            recommendCustomList.add(recommendCustomOutVO);
        }
        return recommendCustomList;
    }
}
