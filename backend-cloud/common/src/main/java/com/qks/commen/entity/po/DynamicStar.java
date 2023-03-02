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
@Table(name = "dynamic_star")
public class DynamicStar extends Model {

    @Column(name = "dynamic_id", comment = "评论id")
    private Long dynamicId;

    @Column(name = "user_id", comment = "用户id")
    private Long userId;
}
