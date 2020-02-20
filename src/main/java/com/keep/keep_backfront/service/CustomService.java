package com.keep.keep_backfront.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.keep.keep_backfront.VO.inVO.custom.CustomListInVO;
import com.keep.keep_backfront.dao.CustomDao;
import com.keep.keep_backfront.entity.Custom;
import com.keep.keep_backfront.handler.exception.ParameterErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/***
 * @author bao
 */
@Service
public class CustomService {

    @Autowired
    private CustomDao customDao;

    // 插入一条习惯信息
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
}
