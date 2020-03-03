package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.VO.inVO.checkin.CheckInRecord;
import com.keep.keep_backfront.entity.CheckIn;
import com.keep.keep_backfront.entity.CheckInComments;
import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.entity.UserLikeCheckIn;
import com.keep.keep_backfront.handler.ArrayJsonHandler;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CheckInDao {

    @Select("select * from check_in where user_id=#{userId} and custome_id=#{customId}")
    @Results(id = "checkInMap", value = {
            @Result(column = "images", property = "images", typeHandler = ArrayJsonHandler.class)
    })
    List<CheckIn> findCheckinByUserAndCustom(Integer userId, Integer customId);


    @Select("select * from check_in where user_id=#{userId} ORDER BY check_in_time DESC")
    @ResultMap("checkInMap")
    List<CheckIn> getCheckInByUser(Integer userId);

    @Select("select * from check_in where custome_id=#{customId} ORDER BY check_in_time DESC")
    @ResultMap("checkInMap")
    List<CheckIn> getCheckInByCustom(Integer customId);

    //根据打卡id查询打卡
    @Select("select * from check_in where id=#{checkInId}")
    @ResultMap("checkInMap")
    CheckIn getCheckInByCheckInId(Integer checkInId);
    //查询打卡的所有评论
    @Select("select * from check_in_comments where check_in_id=#{checkInId} ORDER BY comment_time DESC")
    List<CheckInComments> getCheckInCommentsByCheckInId(Integer checkInId);

    //查询点赞打卡记录的所有用户
    @Select("select * from user where id in " +
            "(select user_id from user_like_check_in where check_in_id=#{checkInId})")
    List<User> getLikeUsersByCheckInId(Integer checkInId);

    //查询用户一个习惯所有的打卡
    @SelectProvider(type = CheckInDaoProvider.class, method = "getCheckIns")
    @ResultMap("checkInMap")
    List<CheckIn> getCheckInsByCustomAndUser(Integer customeId, Integer userId);

    //打卡
    @Insert("insert into check_in(user_id,custome_id,check_in_time,days)" +
            "values(#{userId},#{customeId},#{checkInTime},#{days})")
    Integer insertCheckIn(CheckIn checkIn);
    //取消打卡
    @Delete("DELETE FROM check_in WHERE id=#{checkInId}")
    Integer deleteCheckIn(Integer checkInId);
    //打卡record
    @Update("update check_in set word_content=#{wordContent}," +
            "images=#{images, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}," +
            "voice=#{voice} " +
            "where id=#{id}")
    Integer updateCheckIn(CheckIn checkIn);

    //插入打卡记录评论
    @Insert("insert into check_in_comments(user_id,check_in_id,comment_time,comment_content,reply_to)\n" +
            "values(#{userId},#{checkInId},#{commentTime},#{commentContent},#{replyTo})")
    Integer insertCheckInComment(CheckInComments checkInComments);

    //插入打卡点赞记录
    @Insert("INSERT INTO user_like_check_in(user_id,check_in_id,like_time) " +
            "VALUES (#{userId},#{checkInId},#{likeTime})")
    Integer insertUserLikeCheckIn(UserLikeCheckIn userLikeCheckIn);

    //查询一个打卡的点赞数
    @Select("select count(*) from user_like_check_in where check_in_id=#{checkInId}")
    Integer getLikeCountByCheckIn(Integer checkInId);

    //查询习惯当日打卡总数
    @Select("select count(*) from check_in where date(check_in_time) = curdate() and custome_id=#{customId}")
    Integer getTodayCheckInByCustom(Integer customId);

    //打卡记录对应的习惯
    @Select("select custome_id from check_in where id=#{checkInId}")
    Integer getCustomIdByCheckIn(Integer checkInId);
    //打卡记录对应的用户
    @Select("select user_id from check_in where id=#{checkInId}")
    Integer getUserIdByCheckIn(Integer checkInId);

    //用户习惯最新打卡
    @Select("select * from check_in where user_id=#{userId} and custome_id=#{customId} " +
            "order by check_in_time DESC LIMIT 1")
    CheckIn getCheckInIdByUserAndCustom(Integer userId,Integer customId);

    class CheckInDaoProvider{
        public String getCheckIns(Integer customeId, Integer userId) {
            return new SQL(){{
                SELECT("*");
                FROM("check_in");
                if (customeId != null) WHERE("custome_id=#{customeId}");
                if (userId != null) WHERE("user_id=#{userId}");
                ORDER_BY("check_in_time DESC");
            }}.toString();
        }
    }

    //删除打卡对应所有评论
    @Delete("delete from check_in_comments where check_in_id=#{checkInId}")
    Integer deleteCheckInCommentsByCheckIn(Integer checkInId);
}
