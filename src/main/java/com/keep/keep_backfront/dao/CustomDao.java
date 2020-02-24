package com.keep.keep_backfront.dao;

import com.github.pagehelper.Page;
import com.keep.keep_backfront.entity.Custom;
import com.keep.keep_backfront.entity.JoinCustom;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

@Component
public interface CustomDao {

    @Insert("insert into custom(title, logo, create_user_id, create_time, tags, is_default)" +
            "values(#{title}, #{logo}, #{createUserId}, #{createTime}, " +
            "#{tags, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}, #{isDefault})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer insertCustom(Custom custom);

    @SelectProvider(type = CustomDaoProvider.class, method = "customList")
    List<Custom> customList(Integer userId, String title);

    @Insert("insert into join_custom(user_id,custom_id,join_time,is_public,target_days,is_completed,beans_count,check_days_count)" +
            "values(#{userId},#{customId},#{joinTime},#{isPublic},#{targetDays},#{isCompleted},#{beadsCount}," +
            "#{checkDaysCount})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer joinCustom(JoinCustom joinCustom);

    @Update("update join_custom set user_id=#{userId},custom_id=#{customId},join_time=#{joinTime},is_public=#{isPublic}," +
            "target_days=#{targetDays},is_completed=#{isCompleted},beans_count=#{beansCount},check_days_count=#{checkDaysCount}" +
            "where id = #{id]")
    void updateJoinCustom(JoinCustom joinCustom);

    @Select("select * from join_custom where id = #{id}")
    JoinCustom findJoinCustomById(Integer id);

    @Select("select * from join_custom where user_id=#{userId} and custom_id=#{customId}")
    JoinCustom findJoinCustomByUserAndCustom(Integer userId, Integer customId);

    // 根据条件构建动态sql
    class CustomDaoProvider {
        public String customList(Integer userId, String title) {
            return new SQL(){{
                SELECT("*");
                FROM("custom");
                if (userId != null) WHERE("create_user_id=#{userId}");
                if (!StringUtils.isEmpty(title)) WHERE("title LIKE '%" + title + "%'");
            }}.toString();
        }
    }

}
