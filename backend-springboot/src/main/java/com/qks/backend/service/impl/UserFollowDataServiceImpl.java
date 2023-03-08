package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.UserFansMapper;
import com.qks.backend.entity.po.follow.UserFollowData;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.UserFollowDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 15998
 * @description 针对表【user_fans】的数据库操作Service实现
 * @createDate 2023-02-22 20:03:20
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class UserFollowDataServiceImpl extends ServiceImpl<UserFansMapper, UserFollowData>
        implements UserFollowDataService {

}




