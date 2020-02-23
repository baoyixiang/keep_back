package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.handler.ArrayJsonHandler;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {

    @Insert("insert into user(wechat_id, avatar, name, personal_signature, gender, last_login_time, beans_count, roles) " +
            "values(#{wechatId}, #{avatar}, #{name}, #{personalSignature}, #{gender}, #{lastLoginTime}, #{beansCount}," +
                    "#{roles, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(User entity);

    @Select("select * from user")
    @Results({
            @Result(column = "roles", property = "roles", typeHandler = ArrayJsonHandler.class)
    })
    List<User> listUser();

    //获取用户推荐列表（待完成）
    @Select("select * from user order by id")
    List<User> getUserByPopularity();
}
