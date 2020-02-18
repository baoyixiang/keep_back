package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.entity.Hope;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HopeDao {

    @Insert("insert into hope(word_content,images,voice,create_user_id,create_time,is_anonymous,is_see_self)"+
    "values(#{word_content}," +
            "#{images, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}," +
            "#{voice}," +
            "#{create_user_id}," +
            "#{create_time}," +
            "#{is_anonymous}," +
            "#{is_see_self})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer insertHope(Hope hope);

    @Select("select * from hope")
    List<Hope> allHopesList();

    @Select("select * from hope " +
            "where create_user_id=#{userId}")
    List<Hope>singleHopeList(Integer hopeId);

    @Select("select * from hope order by rand() limit 1")
    Hope oneRandHope();
}
