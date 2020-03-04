package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.VO.inVO.user.UserFollowInVO;
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
    @Insert("insert into user(wechat_id, avatar, name, personal_signature, gender, last_login_time, beans_count, roles, is_recommended) " +
            "values(#{wechatId}, #{avatar}, #{name}, #{personalSignature}, #{gender}, #{lastLoginTime}, #{beansCount}," +
                    "#{roles, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}, #{isRecommended})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int newUser(User entity);

    //根据用户id查询用户
    @Select("select * from user where id=#{id}")
    User getUserById(Integer id);

    @Select("select * from user where wechat_id=#{wechat_id}")
    User getUserByWechatId(String wechat_id);

    //插入关注的记录
    @Insert("insert into user_attention(user_id,followed_user_id,follow_time)" +
            "values(#{userId},#{followedUserId},#{followTime})")
    Integer insertFollowing(UserAttention userAttention);
    //删除关注的记录
    @Delete("DELETE FROM user_attention WHERE user_id=#{userId} AND followed_user_id=#{followedUserId}")
    Integer deleteFollowing(UserFollowInVO userFollowInVO);

    //查找我关注的人
    @Select("select * from user where id in" +
            "(select followed_user_id from user_attention where user_id=#{myId})")
    List<User> followingUser(Integer myId);

    //查找关注我的人(我的粉丝)
    @Select("select * from user where id in" +
            "(select user_id from user_attention where followed_user_id=#{myId})")
    List<User> followsOfMe(Integer myId);

    /**
     * --------------------------------------------------------------------------
     */

    //查询所有用户
    @Select("select * from user")
    @Results({
            @Result(column = "roles", property = "roles", typeHandler = ArrayJsonHandler.class)
    })
    List<User> listUser();

    //获取用户推荐列表（待完成）
    @Select("select * from user where is_recommended=1 having id!=#{userId}")
    List<User> getRecommendedUser(Integer userId);

    //将用户设置为推荐用户
    @Update("UPDATE `user` SET is_recommended=ABS(1-is_recommended) WHERE id=#{userId}")
    Integer setRecommended(Integer userId);

    //查询用户所有习惯数
    @Select("select count(*) from custom where create_user_id=#{userId}")
    Integer getCustomsCountOfUser(Integer userId);
    //查询用户所有心愿数
    @Select("select count(*) from hope where create_user_id=#{userId}")
    Integer getHopesCountOfUser(Integer userId);
}
