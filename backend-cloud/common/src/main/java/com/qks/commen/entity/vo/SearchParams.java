package com.qks.commen.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName SearchParams
 * @Description 管理端查询条件
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-09 12:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParams {
    /**
     * 当前页数
     */
    private Integer currentPage;

    /**
     * 审核状态
     */
    private Integer state;
}
