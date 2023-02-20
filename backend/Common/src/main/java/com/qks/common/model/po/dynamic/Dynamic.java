package com.qks.common.model.po.dynamic;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName Dynamic
 * @Description 动态
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 09:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dynamic extends BaseModel {

    // 用户id
    private Long userId;

    // 动态标题
    private String title;

    // 动态内容
    private String content;
}
