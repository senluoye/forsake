package com.qks.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qks.work.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 15998
 * @description 针对表【user】的数据库操作Mapper
 * @createDate 2023-01-15 12:51:17
 * @Entity generator.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}




