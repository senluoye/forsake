package com.qks.feignclient.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-06-10 16:26
 */
@FeignClient("userservice")
public interface UserClint {

}
