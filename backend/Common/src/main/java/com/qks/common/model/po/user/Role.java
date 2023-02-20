package com.qks.common.model.po.user;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName Role
 * @Description 角色权限
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 08:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseModel {

    // 权限名称
    private String name;

    // 权限描述
    private String description;
}
