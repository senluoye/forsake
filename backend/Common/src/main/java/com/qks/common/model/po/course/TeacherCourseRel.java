package com.qks.common.model.po.course;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName TeacherCourseRel
 * @Description 教师课程联系表
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 14:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TeacherCourseRel extends BaseModel {

    // 教师id
    private Long teacherId;

    // 课程id
    private Long courseId;
}
