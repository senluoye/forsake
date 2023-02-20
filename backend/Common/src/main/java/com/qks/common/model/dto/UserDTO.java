package com.qks.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserDTO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 15:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String code;

    private String password;
}
