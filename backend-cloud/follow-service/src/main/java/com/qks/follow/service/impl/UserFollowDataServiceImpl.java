package com.qks.follow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.follow.dao.UserFansMapper;
import com.qks.common.entity.po.follow.UserFollowData;
import com.qks.follow.service.UserFollowDataService;
import org.springframework.stereotype.Service;

/**
 * @author 15998
 * @description 针对表【user_fans】的数据库操作Service实现
 * @createDate 2023-02-22 20:03:20
 */
@Service
public class UserFollowDataServiceImpl extends ServiceImpl<UserFansMapper, UserFollowData>
        implements UserFollowDataService {

}




