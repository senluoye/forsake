package com.qks.feignclient.service;

import com.qks.feignclient.service.backImpl.DynamicBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "back-user-service", fallback = DynamicBackImpl.class)
public interface BackUserClient {

    @PostMapping("/api/feign/back-user/check-role")
    boolean isAdminOrAuditor(String token);
}
