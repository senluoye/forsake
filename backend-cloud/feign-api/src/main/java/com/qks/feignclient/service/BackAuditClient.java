package com.qks.feignclient.service;

import com.qks.common.entity.po.RecordAudit;
import com.qks.common.entity.po.User;
import com.qks.feignclient.service.backImpl.DynamicBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "back-audit-service", fallback = DynamicBackImpl.class)
public interface BackAuditClient {
    @PostMapping("/api/feign/back-audit")
    RecordAudit insert(RecordAudit recordAudit);
}
