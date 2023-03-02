package com.qks.feignclient.service;

import com.qks.common.entity.po.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 16:26
 */
@FeignClient("user-service")
public interface UserClient {

    @PostMapping("/api/feign/user")
    User selectById(@RequestBody Long userId);
}
