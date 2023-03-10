package com.qks.backuser.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qks.backuser.dao.BackUserRoleRelMapper;
import com.qks.common.entity.po.BackUserRoleRel;
import com.qks.common.exception.ServiceException;
import com.qks.common.utils.JwtUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName BackUserUtil
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-08 23:29
 */
@Component
public class BackUserUtil {

    @Resource
    private BackUserRoleRelMapper backUserRoleRelMapper;

    public Boolean isAdmin(String token) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        List<BackUserRoleRel> rels = backUserRoleRelMapper.selectList(
                new LambdaQueryWrapper<BackUserRoleRel>().eq(BackUserRoleRel::getBackUserId, userId)
        );

        for (BackUserRoleRel rel : rels) {
            if (rel.getRoleId() == 1) {
                return true;
            }
        }

        return false;
    }
}
