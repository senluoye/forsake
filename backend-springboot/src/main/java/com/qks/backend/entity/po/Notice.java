package com.qks.backend.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

/**
 * @ClassName Notice
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 19:12
 */
@Data
@Table(name = "notice")
public class Notice extends Model{

    @Column(comment = "内容")
    private String content;

}
