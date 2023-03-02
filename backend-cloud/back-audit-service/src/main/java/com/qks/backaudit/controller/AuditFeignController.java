package com.qks.backaudit.controller;

import com.qks.common.entity.po.RecordAudit;
import com.qks.work.service.AuditService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AuditFeignController {

    @Resource
    private AuditService auditService;

    @PostMapping("/api/feign/back-audit")
    RecordAudit insert(RecordAudit recordAudit) {
        auditService.save(recordAudit);
        return recordAudit;
    }
}
