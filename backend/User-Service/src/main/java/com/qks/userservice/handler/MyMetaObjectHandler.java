package com.qks.userservice.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @ClassName MyMetaObjectHandler
 * @Description 这个属于Mybatis-plus的配置类，主要是自动填充的功能实现
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-20 10:45
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 当mybatis-plus实现添加操作时执行
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createAt", new Timestamp(System.currentTimeMillis()), metaObject);
        this.setFieldValByName("updateAt", new Timestamp(System.currentTimeMillis()), metaObject);
    }

    /**
     * 实现修改操作时执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateAt", new Date(), metaObject);
    }
}
