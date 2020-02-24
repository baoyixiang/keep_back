package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.entity.User;
import com.keep.keep_backfront.entity.UserAttention;
import com.keep.keep_backfront.handler.ArrayJsonHandler;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {

    //插入用户
    @Insert("insert into user(wechat_id, avatar, name, personal_signature, gender, last_login_time, beans_count, roles) " +
            "values(#{wechatId}, #{avatar}, #{name}, #{personalSignature}, #{gender}, #{lastLoginTime}, #{beansCount}," +
                    "#{roles, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int newUser(User entity);

    //根据用户id查询用户
    @Select("select * from user where id=#{wechatId}")
    User getUserById(String wechatId);

    //查询所有用户
    @Select("select * from user")
    @Results({
            @Result(column = "roles", property = "roles", typeHandler = ArrayJsonHandler.class)
    })
    List<User> listUser();

    //插入关注的记录
    @Insert("insert into user_attention(user_id,followed_user_id,follow_time)" +
            "values(#{userId},#{followedUserId},#{followTime})")
    int insertFollowing(UserAttention userAttention);

    //查找我关注的人
    @Select("select * from user where id in" +
            "(select followed_user_id from user_attention where user_id=#{myId})")
    List<User> followingUser(Integer myId);

    //查找关注我的人(我的粉丝)
    @Select("select * from user where id in" +
            "(select user_id from user_attention where followed_user_id=#{myId})")
    List<User> followsOfMe(Integer myId);
}
