package com.qks.common.entity.vo;

import com.qks.common.entity.enums.PageEnum;
import com.qks.common.entity.po.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

/**
 * @ClassName PageVO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-15 17:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {

    private T data;

    /**
     * 当前页
     * 从 1 开始
     */
    private Long current;

    /**
     * 总共的记录数
     */
    private Long total;

    /**
     * 页大小
     */
    private Long size = PageEnum.DefaultNum.getPageNum();

    public PageVO(Long current, Long total) {
        this.data = (T) new ArrayList<Model>();
        this.current = current;
        this.total = total;
    }

}
