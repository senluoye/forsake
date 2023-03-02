package com.qks.commen.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

/**
 * @ClassName DynamicCollect
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 19:33
 */
@Data
@Table(name = "work_collect")
public class WorkCollect extends Model {

    @Column(name = "work_id", comment = "就业/创业信息id")
    private Long workId;

    @Column(name = "user_id", comment = "用户id")
    private Long userId;
}
