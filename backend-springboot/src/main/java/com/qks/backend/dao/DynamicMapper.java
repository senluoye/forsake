package com.qks.backend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qks.backend.entity.po.Dynamic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 15998
 * @description 针对表【dynamic】的数据库操作Mapper
 * @createDate 2023-01-15 12:51:17
 * @Entity generator.entity.Dynamic
 */
public interface DynamicMapper extends BaseMapper<Dynamic> {

    @Update("update dynamic set like_count = #{likeCount} where id = #{id}")
    void updateLikeCount(@Param("id") Long id, @Param("likeCount") Long likeCount);

    @Select("select count(*) from dynamic, record_audit " +
            "where dynamic.id = record_audit.record_id " +
            "and record_audit.state = #{state} " +
            "and record_audit.flag = #{flag} ")
    long selectTotal(@Param("flag") Integer flag, @Param("state") Integer state);

    @Select("select * from dynamic, record_audit " +
            "where dynamic.id = record_audit.record_id " +
            "and record_audit.state = #{state} " +
            "and record_audit.flag = #{flag} " +
            "limit #{offset}, 20")
    List<Dynamic> selectDynamicList(@Param("flag") Integer flag, @Param("state") Integer state, @Param("offset") Long offset);

    @Update("update dynamic set collect_count = #{collectCount} where id = #{id}")
    void updateCollectCount(@Param("id") Long id, @Param("collectCount") Long collectCount);
}




