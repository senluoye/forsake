package com.qks.common.entity.po.follow;

import com.qks.common.entity.po.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName UserFollow
 * @Description 用户关注关系表
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-21 21:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollow extends Model implements Serializable {
    private Long userId;
    private Long followerId;
}
