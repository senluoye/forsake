package com.qks.backend.entity.po.browser;

import com.qks.backend.entity.po.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Browser
 * @Description 浏览历史记录
 * @Author: Administrator
 * @Version v1.0
 * @Create: 2023-03-08 15:47
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Browser extends Model {

    // 记录id
    private Long recordId;

    // 记录标签
    private Integer flag;

    // 用户id
    private Long userId;

}