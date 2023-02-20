package com.qks.common.model.po.setiment;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName SentimentCommentRel
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 09:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SentimentCommentRel extends BaseModel {

    // 学习心得评论id
    private String sentimentId;

    // 评论id
    private Long commentId;

    // 用户id
    private Long userId;

    // 父id
    private Long parentId;
}
