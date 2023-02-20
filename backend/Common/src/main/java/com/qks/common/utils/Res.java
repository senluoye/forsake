package com.qks.common.utils;

import com.qks.common.model.vo.ResVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Dessert
 * @Description 返回体工具类
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-05 17:55
 */
public class Res {
    public static <T> ResVO<T> success(T data, Integer code, String msg) {
        return new ResVO<T>(data, code, msg);
    }

    public static <T> ResVO<T> success(T data) {
        return new ResVO<T>(data, 0, "success");
    }

    public static <T> ResVO<T> success(T data, String msg) {
        return new ResVO<T>(data, 0, msg);
    }

    public static <T> ResVO<Map<String, Object>> successMap(Object data) {
        Map<String, Object> result = new HashMap<>();
        result.put("data", data);
        return new ResVO<Map<String, Object>>(result, 0, "success");
    }

    public static <T> ResVO<T> error(Integer code, String msg) {
        return new ResVO<T>(null, code, msg);
    }


}
