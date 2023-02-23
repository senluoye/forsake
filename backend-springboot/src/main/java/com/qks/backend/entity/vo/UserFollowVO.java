package com.qks.backend.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserFollow
 * @Description 用户关注关系表
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-21 21:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowVO implements Serializable {
    /**
     * 被关注的人的id
     */
    private Long userId;

    /**
     * 关注人的id
     */
    private Long followerId;
}
