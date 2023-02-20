package com.qks.common.model.po.course;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName Course
 * @Description 课程
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-07 17:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Course extends BaseModel {

    // 课程名称
    private String name;

    // 课程描述
    private String description;

    // 封面文件id
    private String faceImgId;

    // 学期
    private String semester;

    // 课程类别
    private String type;
}
