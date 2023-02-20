package com.qks.backend.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName PageCountVO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-29 18:35
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageCountVO {
    // 页数是从0开始的
    private Long currentPage;
}
