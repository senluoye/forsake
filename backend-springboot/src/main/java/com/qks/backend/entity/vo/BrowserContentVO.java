package com.qks.backend.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BrowserVO
 * @Description 返回浏览记录的vo
 * @Author: Administrator
 * @Version v1.0
 * @Create: 2023-03-08 15:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrowserContentVO {

    // 记录id
    private Long recordId;

    // 记录标签
    private Integer flag;

    // 记录内容
    private String content;

    // 用户id
    private Long userId;

    // 昵称
    private String nickName;

    // 用户头像
    private String avatarUrl;

    // 是否浏览
    private Boolean isBowser;
}
