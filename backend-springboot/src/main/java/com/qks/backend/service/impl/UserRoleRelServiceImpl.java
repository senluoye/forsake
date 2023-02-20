package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.UserMapper;
import com.qks.backend.dao.UserRoleRelMapper;
import com.qks.backend.entity.enums.RoleEnum;
import com.qks.backend.entity.po.UserRoleRel;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.UserRoleRelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 15998
 * @description 针对表【user_role_rel】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class UserRoleRelServiceImpl extends ServiceImpl<UserRoleRelMapper, UserRoleRel>
        implements UserRoleRelService {

    @Resource
    private UserRoleRelMapper userRoleRelMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public Boolean isAdminOrAuditor(Long userId) throws ServiceException {
        if (userMapper.selectById(userId) == null) {
            throw new ServiceException("用户不存在");
        }

        List<Long> roleIdList = userRoleRelMapper.getRolesByUserId(userId);
        for (Long roleId : roleIdList) {
            if (RoleEnum.Admin.getId().equals(roleId) || RoleEnum.Auditor.getId().equals(roleId)) {
                return true;
            }
        }

        return false;
    }

}




