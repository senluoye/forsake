package com.qks.common.helper;


import com.qks.common.entity.vo.ResVO;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ResultHelper
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-19 17:29
 */
public class R {
    public static <T> ResVO<T> success(T data, Integer code, String msg) {
        return new ResVO<T>(data, code, msg);
    }

    public static <T> ResVO<T> success(T data) {
        return success(data, 0, "success");
    }

    public static <T> ResVO<T> success(T data, String msg) {
        return success(data, 0, msg);
    }

    public static <T> ResVO<T> error(Integer code, String msg) {
        return success(null, code, msg);
    }

    public static ResVO<Map<String, Object>> map(String des, Object object) {
        Map<String, Object> data = new HashMap<>();
        data.put(des, object);
        return success(data, 0, null);
    }

    public static ResVO<Map<String, Object>> map(String des, Object object, String msg) {
        Map<String, Object> data = new HashMap<>();
        data.put(des, object);
        return success(data, 0, msg);
    }

}
