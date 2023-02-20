package com.qks.backend.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;

/**
 * @ClassName DynamicFile
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 19:34
 */
@Data
@Table(name = "competition_file")
public class CompetitionFile extends Model {

    @Column(name = "competition_id", comment = "记录id")
    private Long competitionId;

    @Column(comment = "文件路径")
    private String path;

    @Column(comment = "文件后缀")
    private String suffix;
}
