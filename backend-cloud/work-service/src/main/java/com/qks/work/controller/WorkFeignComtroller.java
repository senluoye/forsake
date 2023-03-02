package com.qks.work.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qks.common.entity.po.RecordAudit;
import com.qks.common.entity.po.WorkInfo;
import com.qks.work.service.WorkFileService;
import com.qks.work.service.WorkInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class WorkFeignComtroller {

    @Resource
    private WorkInfoService workInfoService;

    @Resource
    private WorkFileService workFileService;

    @PostMapping("/api/feign/work/list")
    List<WorkInfo> selectList(List<RecordAudit> recordAuditList) {
        return workInfoService.list(
                new LambdaQueryWrapper<WorkInfo>().in(
                        WorkInfo::getId,
                        recordAuditList.stream()
                                .map(RecordAudit::getRecordId)
                                .collect(Collectors.toList())
                )
        );
    }
}
