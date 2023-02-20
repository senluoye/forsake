package com.qks.common.model.po.user;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName UserRoleRel
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 08:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserRoleRel extends BaseModel {
    private Long userId;

    private Long roleId;
}
