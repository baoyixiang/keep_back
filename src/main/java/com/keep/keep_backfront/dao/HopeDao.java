package com.keep.keep_backfront.dao;

import com.keep.keep_backfront.VO.inVO.hope.AddLikeHopeVO;
import com.keep.keep_backfront.VO.outVO.hope.HopeListOutVO;
import com.keep.keep_backfront.entity.Hope;
import com.keep.keep_backfront.entity.HopeComment;
import com.keep.keep_backfront.entity.UserLikeHope;
import com.keep.keep_backfront.handler.ArrayJsonHandler;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HopeDao {

    //插入单个树洞心愿
    @Insert("insert into hope(word_content,images,voice,create_user_id,create_time,is_anonymous,is_see_self,likeCount,CommentCount)"+
    "values(#{wordContent}," +
            "#{images, typeHandler=com.keep.keep_backfront.handler.ArrayJsonHandler}," +
            "#{voice}," +
            "#{createUserId}," +
            "#{createTime}," +
            "#{isAnonymous}," +
            "#{isSeeSelf}," +
            "#{likeCount}," +
            "#{CommentCount})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    Integer insertHope(Hope hope);

    //查询所有树洞心愿
    @Select("SELECT `user`.`name`,`user`.avatar,hope.*\n" +
            "from user\n" +
            "RIGHT JOIN hope\n" +
            "ON hope.create_user_id=`user`.id")
//    @Results({
//            @Result(column = "name",property = "name",jdbcType = JdbcType.VARCHAR),
//            @Result(column = "avatar",property = "avatar",jdbcType = JdbcType.VARCHAR)
//    })
    @Results(id = "hopeListOutVOMap", value = {
            @Result(column = "images", property = "images", typeHandler = ArrayJsonHandler.class)
    })
    List<HopeListOutVO> allHopesList();

    //根据心愿id获取心愿
    @Select("select * from hope where id=#{hopeId}")
    @Results(id = "hopeMap", value = {
            @Result(column = "images", property = "images", typeHandler = ArrayJsonHandler.class)
    })
    Hope getHopeByHopeId(Integer hopeId);

    //根据userId返回该用户所有树洞心愿
    @Select("select * from hope " +
            "where create_user_id=#{userId}")
    @ResultMap("hopeMap")
    List<Hope> HopeListByUser(Integer userId);

    //随机返回一条树洞心愿
    @Select("select * from hope order by rand() limit 1")
    @ResultMap("hopeMap")
    Hope oneRandHope();

    //查询心愿点赞记录
    @Select("select * from user_like_hope where hope_id=#{hopeId}")
    List<UserLikeHope> getUserLikeHopeByHopeId(Integer hopeId);

    //插入一条心愿评论
    @Insert("insert into hope_comments(user_id,hope_id,comment_time,comment_content,reply_to)" +
            "values(#{userId},#{hopeId},#{commentTime},#{commentContent},#{replyTo})")
    Integer insertHopeComment(HopeComment hopeComment);
    //对应心愿得评论数+1
    @Update("update hope set CommentCount=CommentCount+1 where id=#{hopeId}")
    Integer updateHopeCommentCount(Integer hopeId);
    //删除一条心愿评论
    @Delete("delete from hope_comments where id=#{HopeCommentId}")
    Integer deleteHopeComment(Integer HopeCommentId);
    //对应心愿评论数-1
    @Update("update hope set CommentCount=CommentCount-1 where id=#{hopeId}")
    Integer updateHopeCommentCount0(Integer hopeId);

    //点赞，插入一条心愿点赞记录
    @Insert("insert into user_like_hope(user_id,hope_id,like_time,like_state)" +
            "values(#{userId},#{hopeId},#{likeTime},#{likeState})")
    Integer insertUserLikeHope(UserLikeHope userLikeHope);
    //给对应心愿的点赞数+1
    @Update("update hope set likeCount=likeCount+1 where id=#{hopeId}")
    Integer updateLikeCount(UserLikeHope userLikeHope);

    //取消点赞，对应心愿点赞数-1
    @Update("update hope set likeCount=likeCount-1 where id=#{hopeId}")
    Integer cancelLike(AddLikeHopeVO addLikeHopeVO);

    //查询心愿的所有评论
    @Select("select * from hope_comments where hope_id=#{hopeId}")
    List<HopeComment> hopeComments(Integer hopeId);



//    //查询心愿的点赞数
//    @Select("select likeCount distinct from hope_details_view where HopeId=#{hopeId}")
//    Integer hopeLikeCount(Integer hopeId);
//
//    //查询心愿的评论数
//    @Select("select CommentCount distinct from hope_details_view where HopeId=#{hopeId}")
//    Integer hopeCommentCount(Integer hopeId);

    /**
     * -------------------------------------------------------------------------------
     */

    //删除心愿
    @Delete("delete from hope where id=#{hopeId}")
    Integer deleteHope(Integer hopeId);

    //根据心愿id删除所有心愿评论
    @Delete("delete from hope_comments where hope_id=#{hopeId}")
    void deleteHopeCommentsByHopeId(Integer hopeId);

}
