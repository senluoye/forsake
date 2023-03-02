package com.qks.backuser.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qks.backuser.service.BackUserRoleService;
import com.qks.common.entity.po.BackUserRoleRel;
import com.qks.common.exception.ServiceException;
import com.qks.common.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BackUserFeignController {

    @Resource
    private BackUserRoleService backUserRoleService;

    @PostMapping("/api/feign/back-user/check-role")
    public Boolean isAdminOrAuditor(String token) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        List<BackUserRoleRel> rels = backUserRoleService.list(
                new LambdaQueryWrapper<BackUserRoleRel>().eq(BackUserRoleRel::getBackUserId, userId)
        );

        for (BackUserRoleRel rel : rels) {
            if (rel.getRoleId() == 1 || rel.getRoleId() == 2) {
                return true;
            }
        }

        return false;
    }

}
