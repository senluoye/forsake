package com.qks.common.model.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RoleEnum
 * @Description 角色枚举
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 09:43
 */
@AllArgsConstructor
@NoArgsConstructor
public enum RoleEnum {
    Admin(1L, "管理员"),
    Teacher(2L, "教师"),
    Counselor(3L, "辅导员"),
    Student(4L, "学生");

    // 角色id
    private Long roleId;

    // 角色名称
    private String name;
}
