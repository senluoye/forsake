package com.qks.common.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;


/**
 * @ClassName UserRoleRel
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 18:43
 */
@Data
@Table(name = "user_role_rel")
public class UserRoleRel extends Model {

    @Column(name = "user_id", comment = "用户id")
    private Long userId;

    @Column(name = "role_id", comment = "角色id")
    private Long roleId;
}
