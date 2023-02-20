package com.qks.common.model.po.setiment;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName Sentiment
 * @Description 学习心得
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 09:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Sentiment extends BaseModel {

    // 章节id
    private String chapterId;

    // 标题
    private String title;

    // 内容
    private String content;

    // 视频文件id
    private String videoId;
    
}
