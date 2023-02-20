package com.qks.backend.entity.enums.audit;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName AuditState
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 19:17
 */
@Getter
@AllArgsConstructor
public enum AuditStateEnum {

    UnCheck(0, "未审核"),
    Pass(1, "通过"),
    NoPass(-1, "未通过");

    private Integer state;
    private String description;

}
