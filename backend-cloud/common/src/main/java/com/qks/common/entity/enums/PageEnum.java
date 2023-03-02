package com.qks.common.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @ClassName PageEnum
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-16 21:12
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum PageEnum {

    DefaultNum(20L);

    private Long pageNum;
}
