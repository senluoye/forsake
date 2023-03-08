package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.UserFollowMapper;
import com.qks.backend.entity.enums.PageEnum;
import com.qks.backend.entity.po.User;
import com.qks.backend.entity.po.follow.UserFollow;
import com.qks.backend.entity.po.follow.UserFollowData;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.entity.vo.UserFollowListVO;
import com.qks.backend.entity.vo.UserFollowVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.UserFollowDataService;
import com.qks.backend.service.UserFollowService;
import com.qks.backend.service.UserService;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 15998
 * @description 针对表【user_follow】的数据库操作Service实现
 * @createDate 2023-02-22 20:03:20
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
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

        return R.map("userFollowId", userFollow.getId(), "关注成功");
    }

    @Override
    public ResVO<UserFollowData> getFollowData(Long userId) {
        UserFollowData userFollowData = userFollowDataService.getBaseMapper().selectOne(
                new LambdaQueryWrapper<UserFollowData>().eq(UserFollowData::getUserId, userId)
        );
        return R.success(userFollowData);
    }

    @Override
    public ResVO<PageVO<List<UserFollowListVO>>> getFollowList(Long userId, Long currentPage) {
        // 关注的人的总数
        Long total = userFollowMapper.selectCount(
                new LambdaQueryWrapper<UserFollow>().eq(UserFollow::getFollowerId, userId)
        );

        // 分页 关注的人的id
        List<Long> followeIdList = userFollowMapper.selectUserFollowList(
                userId, currentPage * PageEnum.DefaultNum.getPageNum()
        );

        List<UserFollowListVO> data = new ArrayList<>();
        for (Long followerId : followeIdList) {
            User user = userService.getById(followerId);
            UserFollowListVO userFollowListVO = UserFollowListVO.builder()
                    .avatarUrl(user.getAvatarUrl())
                    .nickName(user.getNickName())
                    .userId(followerId)
                    .isFollow(true)
                    .build();
            data.add(userFollowListVO);
        }

        PageVO<List<UserFollowListVO>> pageVO = new PageVO<>();
        pageVO.setTotal(total);
        pageVO.setCurrent(currentPage);
        pageVO.setSize(PageEnum.DefaultNum.getPageNum());
        pageVO.setData(data);
        return R.success(pageVO);
    }
}




