package com.qks.competition.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qks.common.entity.po.Competition;
import com.qks.common.entity.po.RecordAudit;
import com.qks.competition.service.CompetitionFileService;
import com.qks.competition.service.CompetitionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CompetitionFeignController {

    @Resource
    private CompetitionService competitionService;

    @Resource
    private CompetitionFileService competitionFileService;

    @PostMapping("/api/feign/competition/list")
    List<Competition> selectList(List<RecordAudit> recordAuditList) {
        return competitionService.list(
                new LambdaQueryWrapper<Competition>().in(
                        Competition::getId,
                        recordAuditList.stream()
                                .map(RecordAudit::getRecordId)
                                .collect(Collectors.toList())
                )
        );
    }
}
