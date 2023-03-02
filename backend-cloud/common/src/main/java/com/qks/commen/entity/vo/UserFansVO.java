package com.qks.commen.entity.vo;

import com.qks.commen.entity.po.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserFans
 * @Description 用户粉丝表
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-21 21:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFansVO extends Model implements Serializable {
    private Long userId;
    private Long fans;
}
