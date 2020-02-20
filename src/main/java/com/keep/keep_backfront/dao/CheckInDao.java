package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.entity.CheckIn;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CheckInDao {

    @Select("select * from check_in where user_id=#{userId} and custom_id=#{customId}")
    public List<CheckIn> findCheckinByUserAndCustom(Integer userId, Integer customId);
}
