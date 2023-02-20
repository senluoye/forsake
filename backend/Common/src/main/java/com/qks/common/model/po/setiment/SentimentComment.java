package com.qks.common.model.po.setiment;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName SentimentComment
 * @Description 学习心得评论
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 09:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SentimentComment extends BaseModel {

    // 评论内容
    private String content;

    // 视频id
    private String videoId;
}
