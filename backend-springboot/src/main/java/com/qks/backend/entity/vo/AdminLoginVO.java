package com.qks.backend.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName AdminLoginVO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-08 14:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginVO {
    private Long code;
    private String password;
}
