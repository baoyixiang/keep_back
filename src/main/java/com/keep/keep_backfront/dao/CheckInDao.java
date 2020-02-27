package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.entity.CheckIn;
import com.keep.keep_backfront.entity.CheckInComments;
import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.entity.UserLikeCheckIn;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CheckInDao {

    @Select("select * from check_in where user_id=#{userId} and custom_id=#{customId}")
    List<CheckIn> findCheckinByUserAndCustom(Integer userId, Integer customId);

    @Select("select * from check_in where id=#{checkInId}")
    CheckIn getCheckInByCheckInId(Integer checkInId);

    @Select("select * from check_in_comments where check_in_id=#{checkInId}")
    List<CheckInComments> getCheckInCommentsByCheckInId(Integer checkInId);

    //查询点赞打卡记录的所有用户
    @Select("select * from user where id in " +
            "(select user_id from user_like_check_in where check_in_id=#{checkInId})")
    List<User> getLikeUsersByCheckInId(Integer checkInId);

    //查询习惯当前所有的打卡
    @Select("select * from check_in WHERE custome_id=#{customeId} and user_id=#{userId}")
    List<CheckIn> getCheckInsByCustomeId(Integer customeId,Integer userId);
    //插入打卡记录
    @Insert("insert into check_in(user_id,custome_id,check_in_time,word_content,images,voice,days)" +
            "values(#{userId},#{customeId},#{checkInTime},#{wordContent}," +
            "#{images, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}," +
            "#{voice},#{days})")
    Integer insertCheckIn(CheckIn checkIn);
    //删除打卡记录
    @Delete("DELETE FROM check_in WHERE id=#{checkInId}")
    Integer deleteCheckIn(Integer checkInId);

    //插入打卡记录评论
    @Insert("insert into check_in_comments(user_id,check_in_id,comment_time,comment_content,reply_to)\n" +
            "values(#{userId},#{checkInId},#{commentTime},#{commentContent},#{replyTo})")
    Integer insertCheckInComment(CheckInComments checkInComments);

    //插入打卡点赞记录
    @Insert("INSERT INTO user_like_check_in(user_id,check_in_id,like_time) " +
            "VALUES (#{userId},#{checkInId},#{likeTime})")
    Integer insertUserLikeCheckIn(UserLikeCheckIn userLikeCheckIn);

}
