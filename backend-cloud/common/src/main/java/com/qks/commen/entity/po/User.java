package com.qks.commen.entity.po;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import lombok.Data;


/**
 * @ClassName User
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-14 16:12
 */
@Data
@Table(name = "user")
public class User extends Model {

    @Column(comment = "用户名")
    private String nickName;

    @Column(comment = "电话")
    private String phone;

    @Column(comment = "保留字段")
    private Long code;

    @Column(comment = "年龄")
    private Integer age;


    @Column(comment = "邮箱")
    private String email;

    @Column(comment = "密码")
    private String password;

    @Column(comment = "openId")
    private String openId;

    @Column(comment = "sessionKey")
    private String sessionKey;


    /**
     * 微信用户信息
     */
    @Column(comment = "头像")
    private String avatarUrl;
    private String city;
    private String country;
    private Integer gender;
    private String language;
    private String province;

    private String token;
}
