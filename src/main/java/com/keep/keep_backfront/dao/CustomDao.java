package com.keep.keep_backfront.dao;

import com.github.pagehelper.Page;
import com.keep.keep_backfront.entity.Custom;
import com.keep.keep_backfront.entity.JoinCustom;
import com.keep.keep_backfront.handler.ArrayJsonHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public interface CustomDao {

    @Insert("insert into custom(title, logo, create_user_id, create_time, tags, is_default, join_count)" +
            "values(#{title}, #{logo}, #{createUserId}, #{createTime}, " +
            "#{tags, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}, #{isDefault}, #{joinCount})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer insertCustom(Custom custom);

    @SelectProvider(type = CustomDaoProvider.class, method = "customList")
    @Results(id = "customMap", value = {
            @Result(column = "tags", property = "tags", typeHandler = ArrayJsonHandler.class)
    })
    List<Custom> customList(Integer userId, String title, Boolean isDefault);

    @Update("update custom set title=#{title}, logo=#{logo}, create_user_id=#{createUserId}, " +
            "create_time=#{createTime}, tags=#{tags, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}, " +
            "is_default=#{isDefault}, join_count=#{joinCount} where id=#{id}")
    void updateCustom(Custom custom);

    @Insert("insert into join_custom(user_id,custom_id,join_time,is_public,target_days,is_completed,beans_count,check_days_count,is_archive)" +
            "values(#{userId},#{customId},#{joinTime},#{isPublic},#{targetDays},#{isCompleted},#{beansCount}," +
            "#{checkDaysCount},#{isArchive})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer joinCustom(JoinCustom joinCustom);

    @Update("update custom set join_count=#{joinCount} where id=#{id}")
    void updateCustomJoinCount(Custom custom);

    @Update("update join_custom set user_id=#{userId},custom_id=#{customId},join_time=#{joinTime},is_public=#{isPublic}," +
            "target_days=#{targetDays},is_completed=#{isCompleted},beans_count=#{beansCount},check_days_count=#{checkDaysCount} " +
            "where id = #{id}")
    void updateJoinCustom(JoinCustom joinCustom);

    @Delete("delete from custom where id=#{customId}")
    void deleteCustom(Integer customId);

    @Delete("delete from join_custom where custom_id=#{customId}")
    void deleteJoinCustom(Integer customId);

    @Select("select * from custom where id = #{id}")
    Custom findCustomById(Integer id);

    @Select("select * from join_custom where id = #{id}")
    JoinCustom findJoinCustomById(Integer id);

    @Select("select distinct * from join_custom where user_id=#{userId}")
    List<JoinCustom> findJoinCustomsByUserId(Integer userId);

    @Select("select * from join_custom where user_id=#{userId} and custom_id=#{customId}")
    JoinCustom findJoinCustomByUserAndCustom(Integer userId, Integer customId);

    @Select("select distinct * from custom where is_default = 1 and json_contains(tags,'[\"${tagId}\"]')")
    @ResultMap("customMap")
    List<Custom> findRecommendCustomByTag(String tagId);

    @Select("select distinct * from custom where is_default = 1")
    List<Custom> findAllRecommendCustom();

    @Select("SELECT DISTINCT custom_id FROM join_custom WHERE user_id=#{userId}")
    List<Integer> findAddedCustomById(Integer userId);

    // 根据条件构建动态sql
    class CustomDaoProvider {
        public String customList(Integer userId, String title, Boolean isDefault) {
            return new SQL(){{
                SELECT("*");
                FROM("custom");
                if (userId != null) WHERE("create_user_id=#{userId}");
                if (isDefault != null) WHERE("is_default=${isDefault}");
                if (!StringUtils.isEmpty(title)) WHERE("title LIKE '%" + title + "%'");
            }}.toString();
        }
    }

    @Select("select join_count from custom where id=#{customId}")
    Integer getJoinCountByCustom(Integer customId);

    @Select("select check_days_count from join_custom where user_id=#{userId} and custom_id=#{customId}")
    Integer getCheckDaysCountByUserAndCustom(Integer userId,Integer customId);
}
