package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.entity.Custom;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomDao {

    @Insert("insert into custom(title, logo, create_user_id, create_time, tags, is_default)" +
            "values(#{title}, #{logo}, #{createUserId}, #{createTime}, " +
            "#{tags, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}, #{isDefault})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer insertCustom(Custom custom);

    @Select("select * from custom")
    List<Custom> allCustomList();

    @Select("select * from custom where create_user_id=#{userId}")
    List<Custom> customByUserIdList(Integer userId);
}
