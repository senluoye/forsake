package com.qks.backend.controller.follow;

import com.qks.backend.entity.po.follow.UserFollowData;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.entity.vo.UserFollowListVO;
import com.qks.backend.entity.vo.UserFollowVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.UserFollowService;
import com.qks.backend.utls.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    private final UserFollowService userFollowService;

    public FollowerController(UserFollowService userFollowService) {
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

    /**
     * 获取关注的列表
     *
     * @param token
     * @return
     * @throws ServiceException
     */
    @GetMapping("/list")
    public ResVO<PageVO<List<UserFollowListVO>>> getFollowList(@RequestHeader("token") String token,
                                                               @RequestBody Long currentPage) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        return userFollowService.getFollowList(userId, currentPage);
    }
}
