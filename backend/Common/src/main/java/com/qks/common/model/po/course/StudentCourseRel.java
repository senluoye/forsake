package com.qks.common.model.po.course;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName StudentCourseId
 * @Description 学生课程联系表
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 14:34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentCourseRel extends BaseModel {

    // 学生id
    private Long studentId;

    // 课程id
    private Long courseId;

}
