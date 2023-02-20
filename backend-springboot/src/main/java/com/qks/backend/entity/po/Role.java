package com.qks.backend.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;


/**
 * @ClassName Role
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 18:36
 */
@Data
@Table(name = "role")
public class Role extends Model{

    @Column(comment = "权限名")
    private String name;

    @Column(comment = "描述")
    private String description;
}
