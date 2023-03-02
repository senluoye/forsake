package com.qks.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConstantPropertiesUtil
 * @Description 当项目一启动，就会执行该接口的重写方法
 * 主要是为给几个静态字符串赋值
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-27 20:59
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {

    /**
     * 地域节点
     */
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    /**
     * id
     */
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    /**
     * 秘钥
     */
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    /**
     * bucket名称
     */
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}


