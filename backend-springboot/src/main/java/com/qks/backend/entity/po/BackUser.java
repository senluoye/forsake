package com.qks.backend.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName Admin
 * @Description 管理端用户
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-08 14:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "back_user")
public class BackUser extends Model {
    private String name;
    private String code;
    private String password;
    private String email;
    private String phone;

    /**
     * 上属领导工号
     */
    private String superiorCode;
}
