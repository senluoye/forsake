package com.qks.backend.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserFollowListVO {

    // 用户id
    private Long userId;

    // 头像
    private String avatarUrl;

    // 昵称
    private String nickName;

}
