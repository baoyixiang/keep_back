package com.keep.keep_backfront.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.keep.keep_backfront.VO.inVO.custom.*;
import com.keep.keep_backfront.VO.outVO.custom.CustomDetailOutVO;
import com.keep.keep_backfront.VO.outVO.custom.CustomListOutVO;
import com.keep.keep_backfront.dao.CheckInDao;
import com.keep.keep_backfront.dao.CustomDao;
import com.keep.keep_backfront.entity.CheckIn;
import com.keep.keep_backfront.entity.Custom;
import com.keep.keep_backfront.entity.JoinCustom;
import com.keep.keep_backfront.handler.exception.ParameterErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
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
        custom.setJoinCount(1);
        try {
            int effectedNum = customDao.insertCustom(custom);
            if (effectedNum == 1) {
                if (!isDefault) {
                    // 用户要加入习惯
                    JoinCustom joinCustom = new JoinCustom();
                    joinCustom.setCheckDaysCount(0);
                    joinCustom.setBeansCount(0);
                    joinCustom.setCompleted(false);
                    joinCustom.setTargetDays(30);
                    joinCustom.setPublic(true);
                    joinCustom.setJoinTime(new Date());
                    joinCustom.setCustomId(custom.getId());
                    joinCustom.setUserId(request.getUserId());
                    joinCustom.setArchive(false);
                    customDao.joinCustom(joinCustom);
                }
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 这部分是管理员端的
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
     * 获取一个用户加入的习惯列表
     */
    public PageInfo<CustomListOutVO> getUserCustomList(CustomListInVO inVO, Boolean isCreateOnly) {
        PageHelper.startPage(inVO.getPageNo(), inVO.getPageSize());
        try {
            List<JoinCustom> list = customDao.findJoinCustomsByUserId(inVO.getUserId());
            List<CustomListOutVO> result = new ArrayList<>();
            list.stream().forEach(it -> {
                CustomListOutVO outVO = new CustomListOutVO();
                Custom custom = customDao.findCustomById(it.getCustomId());
                if (isCreateOnly) {
                    if (custom.getCreateUserId().equals(inVO.getUserId())) {
                        outVO.setCustom(custom);
                        outVO.setJoinCustom(it);

                        // 看这个用户今天是否在这个习惯上打卡
                        outVO.setIsCheckInToday(isTodayCheckIn(it.getCustomId(), inVO.getUserId()));

                        result.add(outVO);
                    }
                } else {
                    outVO.setJoinCustom(it);
                    outVO.setCustom(custom);
                    outVO.setIsCheckInToday(isTodayCheckIn(it.getCustomId(), inVO.getUserId()));
                    result.add(outVO);
                }
            });
            return new PageInfo<>(result);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("信息获取失败");
        }
    }

    private Boolean isTodayCheckIn(Integer customId, Integer userId) {
        List<CheckIn> checkIns = checkInDao.getCheckInsByCustomAndUser(customId, userId);
        if (checkIns.isEmpty()) {
            return false;
        } else {
            Calendar lastCalendar = Calendar.getInstance();
            lastCalendar.setTime(checkIns.get(0).getCheckInTime());
            Calendar nowCalendar = Calendar.getInstance();
            Boolean isSameYear = nowCalendar.get(Calendar.YEAR) == lastCalendar.get(Calendar.YEAR);
            Boolean isSameDay = nowCalendar.get(Calendar.DAY_OF_YEAR) == lastCalendar.get(Calendar.DAY_OF_YEAR);
            return isSameYear && isSameDay;
        }
    }
    /**
     * 用户加入一个习惯
     */
    public ResponseEntity joinCustom(JoinCustomInVO inVO) {
        int targetDay;
        if(inVO.getTargetDay()!=null){
            targetDay = inVO.getTargetDay();
        }else{
            targetDay = 30;
        }
        JoinCustom joinCustom = new JoinCustom();
        joinCustom.setUserId(inVO.getUserId());
        joinCustom.setCustomId(inVO.getCustomId());
        joinCustom.setJoinTime(new Date());
        joinCustom.setPublic(inVO.getIsPublic());
        joinCustom.setTargetDays(targetDay);
        joinCustom.setCompleted(false);
        joinCustom.setBeansCount(inVO.getBeansCount());
        joinCustom.setCheckDaysCount(0);
        joinCustom.setArchive(false);
        Custom custom = customDao.findCustomById(joinCustom.getCustomId());
        List<Integer> addedCustomList = customDao.findAddedCustomById(joinCustom.getUserId());
        custom.setJoinCount(custom.getJoinCount()+1);
        System.out.println("2:参加人数+1");
        try {
            if(!addedCustomList.contains(joinCustom.getCustomId())) {
                customDao.joinCustom(joinCustom);
                customDao.updateCustomJoinCount(custom);
            }else {
                return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity updateJoinCustom(JoinCustomInVO inVO) {
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
            customDao.deleteJoinCustom(customId);
            checkInDao.getCheckInsByCustomAndUser(customId, null).forEach(it -> {
                checkInDao.deleteCheckIn(it.getId());
                checkInDao.deleteCheckInCommentsByCheckIn(it.getId());
                checkInDao.deleteLikeCheckIn(it.getId());
            });
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
        List<Integer> customList = customDao.findAddedCustomById(request.getUserId());
        List<Custom> recommendCustomList;
        if(request.getTag() == null) {
            recommendCustomList = customDao.findAllRecommendCustom();
        }else {
            recommendCustomList = customDao.findRecommendCustomByTag(request.getTag());
        }
        for(int i = recommendCustomList.size() - 1; i >= 0; i--) {
            if(customList.contains(recommendCustomList.get(i).getId())) {
                recommendCustomList.remove(recommendCustomList.get(i));
            }
        }
        return recommendCustomList;
    }
}
