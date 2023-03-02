package com.qks.common.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName RoleEnum
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 18:36
 */
@Getter
@AllArgsConstructor
public enum RoleEnum {

    Admin(1L, "管理员"),
    Teacher(2L, "教师"),
    Student(3L, "学生"),
    Auditor(4L, "运营");

    private Long id;

    private String name;

    private String description;

    RoleEnum(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
