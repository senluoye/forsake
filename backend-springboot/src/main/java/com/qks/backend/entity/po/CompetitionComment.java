package com.qks.backend.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

/**
 * @ClassName DynamicComment
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 19:29
 */
@Data
@Table(name = "competition_comment")
public class CompetitionComment extends Model {

    @Column(name = "user_id", comment = "用户id")
    private Long userId;

    @Column(comment = "内容")
    private String content;

    @Column(name = "parent_comment_id", comment = "父记录id")
    private Long parentCommentId;

    @Column(name = "like_count", comment = "点赞数")
    private Long likeCount;

    @Column(name = "competition_id", comment = "动态id")
    private Long competition_idId;

}
