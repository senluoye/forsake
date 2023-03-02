package com.qks.common.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

/**
 * @ClassName WorkInfo
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-15 13:08
 */
@Data
@Table(name = "work_info")
public class WorkInfo extends Model {

    @Column(comment = "标题")
    private String title;

    @Column(comment = "内容")
    private String content;

    @Column(comment = "组织者")
    private String organizer;

    @Column(comment = "用户id")
    private Long userId;
}
