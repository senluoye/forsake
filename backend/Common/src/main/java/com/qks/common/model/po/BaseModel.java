package com.qks.common.model.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName BaseModel
 * @Description 基类
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-07 17:24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel {

    private Long id;

    private Timestamp createAt;

    private Timestamp updateAt;

    private Timestamp deleteAt;
}
