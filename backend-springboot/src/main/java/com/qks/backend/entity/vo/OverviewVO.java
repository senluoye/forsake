package com.qks.backend.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName OverviewVO
 * @Description 数据总览
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-10 17:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OverviewVO {
    // 用户数量
    private Long userNumber;

    // 审核员数量
    private Long auditorNumber;

    // 学习动态数量
    private Long dynamicNumber;

    // 竞赛动态数量
    private Long competitionNumber;

    // 就业/创业动态数量
    private Long workNumber;

}
