package com.qks.commen.entity.po;

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
@Table(name = "work_file")
public class WorkFile extends Model {

    @Column(name = "work_id", comment = "记录id")
    private Long workId;

    @Column(comment = "文件路径")
    private String path;

    @Column(comment = "文件后缀")
    private String suffix;
}
