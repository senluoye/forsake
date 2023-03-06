package com.qks.backend.entity.vo;

import com.qks.backend.entity.po.DynamicFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName DynamicVO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-22 18:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DynamicVO {
    // 动态id
    private Long id;

    // 昵称
    private String nickName;

    // 用户头像
    private String avatarUrl;

    // 用它id
    private Long userId;

    // 创建时间
    private Timestamp createAt;

    // 更新时间
    private Timestamp updateAt;

    // 内容
    private String content;

    // 文件列表
    private List<DynamicFile> dynamicFileList;

    // 点赞书
    private Long likeCount;

    // 收藏数
    private Long collectCount;

    // 评论数
    private Long commentCount;

    // 是否点赞
    private Boolean isLike;

    // 是否收藏
    private Boolean isCollect;

    // 是否关注
    private Boolean isFollow;

    // 审核相关
    private String message;
    private Integer state;
    private Long recordId;
    private String title;

}
