package com.qks.backend.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Dynamic
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 19:27
 */
@Data
@Table(name = "dynamic")
@TableName(value = "dynamic")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dynamic extends Model {

    @Column(name = "user_id", comment = "用户id")
    private Long userId;

    @Column(comment = "内容")
    private String content;

    @Column(comment = "标题")
    private String title;

    @Column(name = "read_count", comment = "阅读数")
    private Long readCount;

    @Column(name = "like_count", comment = "点赞数")
    private Long likeCount;

    @Column(name = "collect_count", comment = "收藏数")
    private Long collectCount;

    @Column(name = "comment_count", comment = "评论数")
    private Long commentCount;

}
