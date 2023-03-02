package com.qks.work.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.common.entity.po.Competition;
import com.qks.common.entity.po.RecordAudit;
import com.qks.common.entity.po.WorkInfo;
import com.qks.common.exception.ServiceException;
import com.qks.common.entity.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-30 19:10
 */
public interface AuditService extends IService<RecordAudit> {
    ResVO<PageVO<List<DynamicVO>>> getDynamicList(String token, SearchParams currentPage) throws ServiceException;

    ResVO<List<Competition>> getCompetitionList(String token, SearchParams currentPage) throws ServiceException;

    ResVO<List<WorkInfo>> getWorkInfoList(String token, SearchParams currentPage) throws ServiceException;

    ResVO<Map<String, Object>> auditDynamic(String token, RecordAuditVO recordAudit) throws ServiceException;

    ResVO<Map<String, Object>> auditCompetition(String token, RecordAuditVO recordAudit) throws ServiceException;

    ResVO<Map<String, Object>> auditWorkInfo(String token, RecordAuditVO recordAudit) throws ServiceException;
}
