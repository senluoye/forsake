package com.qks.backend.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName WxAuthDTO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-24 10:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxAuthDTO {

    private String session_key;

    private String openid;
}
