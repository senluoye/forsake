package com.qks.backend.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: BrowserVO
 * @Description
 * @Author: Administrator
 * @Version v1.0
 * @Create: 2023-03-08 15:58
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrowserVO {

    // 记录id
    private Long recordId;

    // 记录标签
    private Integer flag;

}
