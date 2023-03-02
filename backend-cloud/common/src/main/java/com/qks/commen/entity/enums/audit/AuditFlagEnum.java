package com.qks.commen.entity.enums.audit;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName RecordFlagEnum
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-08 23:16
 */
@Getter
@AllArgsConstructor
public enum AuditFlagEnum {

    Dynamic(1, "学习"),
    Competition(2, "竞赛"),
    Work(3, "就业/创业");

    private Integer flag;
    private String description;
}
