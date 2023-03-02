package com.qks.commen.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

/**
 * @ClassName Suggestion
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 19:11
 */
@Data
@Table(name = "suggestion")
public class Suggestion extends Model{

    @Column(name = "user_id", comment = "用户id")
    private Long userId;

    @Column(comment = "内容")
    private String content;
}
