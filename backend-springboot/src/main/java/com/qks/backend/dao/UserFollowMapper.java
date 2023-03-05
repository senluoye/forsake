package com.qks.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qks.backend.entity.po.follow.UserFollow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 15998
 * @description 针对表【user_follow】的数据库操作Mapper
 * @createDate 2023-02-22 20:03:20
 * @Entity generator.entity.UserFollow
 */
@Mapper
public interface UserFollowMapper extends BaseMapper<UserFollow> {

    @Select("select follower_id from user_follow where user_id = #{userId} limit #{offset}, 20")
    List<Long> selectUserFollowList(@Param("userId") Long userId, @Param("offset") Long offset);
}




