package com.keep.keep_backfront.dao;

import com.github.pagehelper.Page;
import com.keep.keep_backfront.entity.Custom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
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
