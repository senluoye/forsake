package com.qks.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.user.dao.RoleMapper;
import com.qks.common.entity.po.Role;
import com.qks.common.exception.ServiceException;
import com.qks.user.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 15998
 * @description 针对表【role】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
        implements RoleService {

}




