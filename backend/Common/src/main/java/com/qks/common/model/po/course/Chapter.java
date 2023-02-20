package com.qks.common.model.po.course;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName Chapter
 * @Description 章节
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 09:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Chapter extends BaseModel {

    // 课程id
    private Long courseId;

    // 章节名称
    private String name;

    // 章节描述
    private String description;

    // 视频文件id
    private String videoId;
}
