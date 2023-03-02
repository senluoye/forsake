package com.qks.follow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.common.entity.po.follow.UserFollow;
import com.qks.common.entity.po.follow.UserFollowData;
import com.qks.common.entity.vo.ResVO;
import com.qks.common.entity.vo.UserFollowVO;
import com.qks.common.exception.ServiceException;

import java.util.Map;

/**
 * @author 15998
 * @description 针对表【user_follow】的数据库操作Service
 * @createDate 2023-02-22 20:03:20
 */
public interface UserFollowService extends IService<UserFollow> {

    ResVO<Map<String, Object>> followSomeOne(UserFollowVO userFollowVO) throws ServiceException;

    ResVO<UserFollowData> getFollowData(Long userId);
}
