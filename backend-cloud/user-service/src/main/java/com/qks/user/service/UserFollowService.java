package com.qks.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.work.entity.po.follow.UserFollow;
import com.qks.work.entity.po.follow.UserFollowData;
import com.qks.work.entity.vo.ResVO;
import com.qks.work.entity.vo.UserFollowVO;
import com.qks.work.exception.ServiceException;

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
