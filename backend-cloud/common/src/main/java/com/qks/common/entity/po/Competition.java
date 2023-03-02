package com.qks.common.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

/**
 * @ClassName Competition
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-15 10:28
 */
@Data
@Table(name = "competition")
public class Competition extends Model {

    @Column(comment = "标题")
    private String title;

    @Column(name = "start_time", comment = "开始时间")
    private String startTime;

    @Column(name = "end_time", comment = "结束时间")
    private String endTime;

    @Column(comment = "内容")
    private String content;

    @Column(comment = "组织单位")
    private String organizer;

    @Column(name = "user_id", comment = "用户id")
    private Long userId;
}
