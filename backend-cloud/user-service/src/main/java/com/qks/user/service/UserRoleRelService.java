package com.qks.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.backend.entity.po.UserRoleRel;
import com.qks.backend.exception.ServiceException;

/**
 * @author 15998
 * @description 针对表【user_role_rel】的数据库操作Service
 * @createDate 2023-01-15 12:51:17
 */
public interface UserRoleRelService extends IService<UserRoleRel> {

    Boolean isAdminOrAuditor(Long userId) throws ServiceException;
}
