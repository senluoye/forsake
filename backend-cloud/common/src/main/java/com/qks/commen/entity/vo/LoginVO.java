package com.qks.commen.entity.vo;

import com.qks.commen.entity.po.User;
import lombok.Data;

/**
 * @ClassName LoginVO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-17 12:19
 */
@Data
public class LoginVO {

    /**
     * 微信code
     */
    private String code;

    /**
     * 微信用户信息
     */
    private User user;
}
