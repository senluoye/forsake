package com.qks.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qks.backend.entity.po.UserRoleRel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 15998
 * @description 针对表【user_role_rel】的数据库操作Mapper
 * @createDate 2023-01-15 12:51:17
 * @Entity generator.entity.UserRoleRel
 */
@Mapper
public interface UserRoleRelMapper extends BaseMapper<UserRoleRel> {

    @Select("select role_id from user_role_rel where user_id = #{userId}")
    List<Long> getRolesByUserId(@Param("userId") Long userId);
}




