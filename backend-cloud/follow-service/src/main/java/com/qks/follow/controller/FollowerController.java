package com.qks.follow.controller;

import com.qks.work.entity.po.follow.UserFollowData;
import com.qks.work.entity.vo.ResVO;
import com.qks.work.entity.vo.UserFollowVO;
import com.qks.work.exception.ServiceException;
import com.qks.work.service.UserFollowDataService;
import com.qks.work.service.UserFollowService;
import com.qks.work.utls.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @ClassName FollowerController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-22 20:07
 */
@RestController
@RequestMapping("/api/follow")
public class FollowerController {

    private final UserFollowDataService userFollowDataService;

    private final UserFollowService userFollowService;

    public FollowerController(UserFollowDataService userFollowDataService, UserFollowService userFollowService) {
        this.userFollowDataService = userFollowDataService;
        this.userFollowService = userFollowService;
    }

    /**
     * 关注/取消关注某人
     *
     * @param token
     * @param userFollowVO
     * @return
     * @throws ServiceException
     */
    @PostMapping
    public ResVO<Map<String, Object>> followSomeOne(@RequestHeader("token") String token,
                                                    @RequestBody UserFollowVO userFollowVO) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        userFollowVO.setFollowerId(userId);
        return userFollowService.followSomeOne(userFollowVO);
    }

    /**
     * 获取关注/粉丝数据
     *
     * @param token
     * @return
     * @throws ServiceException
     */
    @GetMapping
    public ResVO<UserFollowData> getFollowData(@RequestHeader("token") String token) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        return userFollowService.getFollowData(userId);
    }
}
