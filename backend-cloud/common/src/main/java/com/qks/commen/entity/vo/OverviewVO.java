package com.qks.commen.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName OverviewVO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-10 17:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OverviewVO {
    private Long userNumber;
    private Long auditorNumber;

}
