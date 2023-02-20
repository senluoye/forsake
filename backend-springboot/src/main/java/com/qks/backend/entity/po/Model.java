package com.qks.backend.entity.po;

import com.baomidou.mybatisplus.annotation.*;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


/**
 * @ClassName Model
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 15:54
 */
@Getter
@Setter
public class Model {

    @IsKey                       //actable主键注解
    @IsAutoIncrement             //自增
    @Column
    @TableId(type = IdType.AUTO) //mybatis-plus主键注解
    private Long id;

    @Column(name = "create_at", comment = "创建时间")
    private Timestamp createAt;

    @Column(name = "update_at", comment = "修改时间")
    @TableField(fill = FieldFill.UPDATE)
    private Timestamp updateAt;

    @TableLogic
    @Column(comment = "删除标记")
    private Integer deleted;
}
