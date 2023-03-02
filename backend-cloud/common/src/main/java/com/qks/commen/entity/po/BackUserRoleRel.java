package com.qks.commen.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @ClassName UserRoleRel
 * @Description 管理端用户角色联系表
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 18:43
 */
@Data
@Table(name = "back_user_role_rel")
@AllArgsConstructor
@NoArgsConstructor
public class BackUserRoleRel extends Model {

    @Column(name = "back_user_id", comment = "用户id")
    private Long backUserId;

    @Column(name = "role_id", comment = "角色id")
    private Long roleId;
}
