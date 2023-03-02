package com.qks.feignclient.service;

import com.qks.common.entity.po.Competition;
import com.qks.common.entity.po.RecordAudit;
import com.qks.feignclient.service.backImpl.DynamicBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "competition-service", fallback = DynamicBackImpl.class)
public interface CompetitionClient {

    @PostMapping("/api/feign/competition/list")
    List<Competition> selectList(List<RecordAudit> recordAuditList);
}
