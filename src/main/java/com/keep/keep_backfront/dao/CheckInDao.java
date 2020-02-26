package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.entity.CheckIn;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CheckInDao {

    @Select("select * from check_in where user_id=#{userId} and custom_id=#{customId}")
    public List<CheckIn> findCheckinByUserAndCustom(Integer userId, Integer customId);

    //查询习惯当前所有的打卡
    @Select("select * from check_in WHERE custome_id=#{customeId}")
    public List<CheckIn> getCheckInsByCustomeId(Integer customeId);
    //插入打卡记录
    @Insert("insert into check_in(user_id,custome_id,check_in_time,word_content,images,voice,days)" +
            "values(#{userId},#{customeId},#{checkInTime},#{wordContent}," +
            "#{images, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}," +
            "#{voice},#{days})")
    public Integer insertCheckIn(CheckIn checkIn);

}
