package com.qks.work.controller;

import com.qks.common.entity.po.Competition;
import com.qks.common.entity.po.WorkInfo;
import com.qks.common.entity.vo.*;
import com.qks.common.exception.ServiceException;
import com.qks.work.service.AuditService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AuditController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-08 23:06
 */
@RestController
@RequestMapping("/api/audit")
public class AuditController {

    @Resource
    private AuditService auditService;

    /**
     * 管理员获取用户发布的动态列表
     *
     * @param searchParams
     * @param token
     * @return
     * @throws ServiceException
     */
    @PostMapping("/list/dynamic")
    public ResVO<PageVO<List<DynamicVO>>> getDynamicList(@RequestBody SearchParams searchParams,
                                                         @RequestHeader("token") String token) throws ServiceException {
        return auditService.getDynamicList(token, searchParams);
    }

    /**
     * 管理员获取用户发布的动态列表
     *
     * @param searchParams
     * @param token
     * @return
     * @throws ServiceException
     */
    @PostMapping("/list/competition")
    public ResVO<List<Competition>> getCompetitionList(@RequestBody SearchParams searchParams,
                                                       @RequestHeader("token") String token) throws ServiceException {
        return auditService.getCompetitionList(token, searchParams);
    }

    /**
     * 管理员获取用户发布的动态列表
     *
     * @param searchParams
     * @param token
     * @return
     * @throws ServiceException
     */
    @PostMapping("/list/work-info")
    public ResVO<List<WorkInfo>> getWorkInfoList(@RequestBody SearchParams searchParams,
                                                 @RequestHeader("token") String token) throws ServiceException {
        return auditService.getWorkInfoList(token, searchParams);
    }

    @PutMapping("/dynamic")
    public ResVO<Map<String, Object>> auditDynamic(@RequestHeader("token") String token,
                                                   @RequestBody RecordAuditVO recordAuditVO) throws ServiceException {
        return auditService.auditDynamic(token, recordAuditVO);
    }

    @PutMapping("/competition")
    public ResVO<Map<String, Object>> auditCompetition(@RequestHeader("token") String token,
                                                       @RequestBody RecordAuditVO recordAuditVO) throws ServiceException {
        return auditService.auditCompetition(token, recordAuditVO);
    }

    @PutMapping("/workInfo")
    public ResVO<Map<String, Object>> auditWorkInfo(@RequestHeader("token") String token,
                                                    @RequestBody RecordAuditVO recordAuditVO) throws ServiceException {
        return auditService.auditWorkInfo(token, recordAuditVO);
    }

}
