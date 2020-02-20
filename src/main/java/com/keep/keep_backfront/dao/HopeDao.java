package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.entity.Hope;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HopeDao {

    //插入单个树洞心愿
    @Insert("insert into hope(word_content,images,voice,create_user_id,create_time,is_anonymous,is_see_self)"+
    "values(#{wordContent}," +
            "#{images, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}," +
            "#{voice}," +
            "#{createUserId}," +
            "#{createTime}," +
            "#{isAnonymous}," +
            "#{isSeeSelf})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer insertHope(Hope hope);

    //查询所有树洞心愿
    @Select("select * from hope")
    List<Hope> allHopesList();

    //根据userId返回该用户所有树洞心愿
    @Select("select * from hope " +
            "where create_user_id=#{userId}")
    List<Hope>HopeListByUser(Integer userId);

    //随机返回一条树洞心愿
    @Select("select * from hope order by rand() limit 1")
    Hope oneRandHope();
}
