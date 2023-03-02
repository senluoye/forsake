package com.qks.commen.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName RecordAudit
 * @Description 审核记录表
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 19:14
 */
@Data
@Table(name = "record_audit")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordAudit extends Model {

    @Column(name = "record_id", comment = "记录id")
    private Long recordId;

    @Column(comment = "所属记录类型：学习/竞赛/就业")
    private Integer flag;

    @Column(name = "auditor_id", comment = "审核员id")
    private Long auditorId;

    @Column(comment = "审核意见")
    private String message;

    @Column(comment = "状态：未审核/通过/未通过")
    private Integer state;
}
