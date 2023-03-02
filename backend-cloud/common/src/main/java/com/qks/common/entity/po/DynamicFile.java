package com.qks.common.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName DynamicFile
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 19:34
 */
@Data
@Table(name = "dynamic_file")
@AllArgsConstructor
@NoArgsConstructor
public class DynamicFile extends Model {

    @Column(name = "dynamic_id", comment = "记录id")
    private Long dynamicId;

    @Column(comment = "文件路径")
    private String path;

    @Column(comment = "文件后缀")
    private String suffix;
}
