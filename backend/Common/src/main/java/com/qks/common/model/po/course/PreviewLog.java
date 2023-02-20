package com.qks.common.model.po.course;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName PreviewLog
 * @Description 预习记录
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 09:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PreviewLog extends BaseModel {

    // 课程id
    private Long courseId;

    // 章节id
    private Long chapterId;

    // 用户id
    private Long userId;
}
