package com.qks.common.model.po.user;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName User
 * @Description 角色
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-07 17:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class User extends BaseModel {

    // 姓名
    private String name;

    // 电话
    private String phone;

    // 学号/工号
    private String code;

    // 年龄
    private String age;

    // 邮件
    private String email;

    // 密码
    private String password;
}
