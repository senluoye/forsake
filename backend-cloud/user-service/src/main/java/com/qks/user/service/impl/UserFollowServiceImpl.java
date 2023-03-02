package com.qks.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.user.dao.UserFollowMapper;
import com.qks.work.entity.po.follow.UserFollow;
import com.qks.work.entity.po.follow.UserFollowData;
import com.qks.work.entity.vo.ResVO;
import com.qks.work.entity.vo.UserFollowVO;
import com.qks.work.exception.ServiceException;
import com.qks.user.service.UserFollowDataService;
import com.qks.user.service.UserFollowService;
import com.qks.user.service.UserService;
import com.qks.work.utls.R;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 15998
 * @description 针对表【user_follow】的数据库操作Service实现
 * @createDate 2023-02-22 20:03:20
 */
@Service
public class UserFollowServiceImpl extends ServiceImpl<UserFollowMapper, UserFollow>
        implements UserFollowService {

    private final UserFollowMapper userFollowMapper;
    private final UserFollowDataService userFollowDataService;
    private final UserService userService;

    public UserFollowServiceImpl(UserFollowMapper userFollowMapper, UserFollowDataService userFollowDataService,
                                 UserService userService) {
        this.userFollowMapper = userFollowMapper;
        this.userFollowDataService = userFollowDataService;
        this.userService = userService;
    }

    @Override
    public ResVO<Map<String, Object>> followSomeOne(UserFollowVO userFollowVO) throws ServiceException {
        Long userId = userFollowVO.getUserId();
        Long followerId = userFollowVO.getFollowerId();

        if (userService.getById(userId) == null) {
            throw new ServiceException("您关注的用户不存在");
        }

        if (userService.getById(followerId) == null) {
            throw new ServiceException("当前用户不存在");
        }

        // 查询数据库是否存在关注关系
        UserFollow userFollow = userFollowMapper.selectOne(
                new LambdaQueryWrapper<UserFollow>()
                        .eq(UserFollow::getFollowerId, userFollowVO.getFollowerId())
                        .eq(UserFollow::getUserId, userFollowVO.getUserId())
        );

        // 存在关注关系
        if (userFollow != null) {
            userFollowMapper.deleteById(userFollow);

            UserFollowData userFollowData = userFollowDataService.getOne(
                    new LambdaQueryWrapper<UserFollowData>().eq(UserFollowData::getUserId, userId)
            );
            userFollowData.setFans(userFollowData.getFans() - 1);
            userFollowDataService.updateById(userFollowData);

            return R.map("userFollowId", userFollow.getId(), "取消关注成功");
        }

        // 不存在关注关系
        userFollow = new UserFollow(userId, followerId);
        if (userFollowMapper.insert(userFollow) < 1) {
            throw new ServiceException("关注失败");
        }

        UserFollowData userFollowData = userFollowDataService.getOne(
                new LambdaQueryWrapper<UserFollowData>().eq(UserFollowData::getUserId, userId)
        );
        if (userFollowData == null) {
            userFollowData = new UserFollowData(userId, 0L, 0L);
            userFollowDataService.save(userFollowData);
        }
        userFollowData.setFans(userFollowData.getFans() + 1);
        userFollowDataService.updateById(userFollowData);

        return R.map("userFollowId", userFollow.getId());
    }

    @Override
    public ResVO<UserFollowData> getFollowData(Long userId) {
        UserFollowData userFollowData = userFollowDataService.getBaseMapper().selectOne(
                new LambdaQueryWrapper<UserFollowData>().eq(UserFollowData::getUserId, userId)
        );
        return R.success(userFollowData);
    }
}




