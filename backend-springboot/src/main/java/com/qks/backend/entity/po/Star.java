package com.qks.backend.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DynamicCollect
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 19:33
 */
@Data
@Table(name = "dynamic_star")
@AllArgsConstructor
@NoArgsConstructor
public class Star extends Model {

    @Column(name = "dynamic_id", comment = "动态id")
    private Long recordId;

    private Integer flag;

    @Column(name = "user_id", comment = "用户id")
    private Long userId;
}
