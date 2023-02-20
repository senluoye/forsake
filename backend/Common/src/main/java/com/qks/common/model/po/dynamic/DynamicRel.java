package com.qks.common.model.po.dynamic;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName DynamicComment
 * @Description 动态评论
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 09:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DynamicRel extends BaseModel {

    // 动态id
    private Long dynamicId;

    // 父id
    private Long parentId;

}
