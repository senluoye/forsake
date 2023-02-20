package com.qks.userservice.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qks.common.model.po.user.UserRoleRel;
import com.qks.common.utils.JwtUtils;
import com.qks.userservice.mapper.UserMapper;
import com.qks.userservice.mapper.UserRoleRelMapper;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-08 15:30
 */
@Component
public class UserUtils {

    @Resource
    private UserRoleRelMapper userRoleRelMapper;

    @Resource
    private Jedis jedis;

    public boolean isAdmin(Integer userId) {
        UserRoleRel relations = userRoleRelMapper.selectOne(
                new QueryWrapper<UserRoleRel>().eq("user_id", userId));

        return relations.getUserId() == 0 && relations.getRoleId() == 0 || relations.getRoleId() > 2;
    }

    /**
     * 查看是否是最新登陆
     * 如果是，就返回 true
     * 否则返回 false
     *
     * @param nowToken
     * @return
     */
    public boolean checkLogin(String nowToken) {
        Integer userId;
        try {
            userId = Integer.valueOf(JwtUtils.parser(nowToken).get("userId").toString());
            if (userId.equals(0))
                return false;
        } catch (Exception e) {
            return false;
        }

        String oldToken = jedis.get(userId.toString());
        return oldToken != null && !oldToken.equals("") && oldToken.equals(nowToken);
    }
}
