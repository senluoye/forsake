package com.qks.backend.entity.vo;

import com.qks.backend.entity.po.DynamicFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrontendDynamicItemVO {
    // 动态id
    private Long id;

    //
    private Long recordId;

    // 昵称
    private String nickName;

    // 用户头像
    private String avatarUrl;

    // 创建时间
    private Timestamp createAt;

    // 更新时间
    private Timestamp updateAt;

    // 内容
    private String content;

    // 文件列表
    private List<DynamicFile> dynamicFileList;

    // 点赞数量
    private Long likeCount;

    // 收藏数量
    private Long collectCount;

    // 评论数量
    private Long commentCount;

    // 是否喜欢
    private Boolean isLike;

    // 是否收藏
    private Boolean isCollect;
}
