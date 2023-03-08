package com.qks.backend.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StarDynamicVO {
    // 记录id
    private Long dynamicId;

    // 记录类型
    private Integer flag;

    // 昵称
    private String nickName;

    // 头像
    private String avatarUrl;

    // 是否点赞
    private Boolean isStar;

    // 内容
    private String content;
}
