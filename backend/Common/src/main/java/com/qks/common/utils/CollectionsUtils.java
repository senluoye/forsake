package com.qks.common.utils;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName Dessert
 * @Description 集合工具类
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-09 15:15
 */
@Component
public class CollectionsUtils {
    /**
     * List去重
     * @param list
     * @return
     */
    public static List<Integer> RemoveRepByMap(List<Integer> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }
}
