package com.qks.feignclient.service;

import com.qks.common.entity.po.RecordAudit;
import com.qks.common.entity.po.WorkInfo;
import com.qks.feignclient.service.backImpl.DynamicBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "work-service", fallback = DynamicBackImpl.class)
public interface WorkClient {

    @PostMapping("/api/feign/work/list")
    List<WorkInfo> selectList(List<RecordAudit> recordAuditList);
}
