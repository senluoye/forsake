package com.qks.backend.service.impl;

import com.qks.backend.dao.CompetitionMapper;
import com.qks.backend.dao.DynamicMapper;
import com.qks.backend.dao.WorkInfoMapper;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName AdminServiceImpl
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-30 19:11
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class AdminServiceImpl implements AdminService {

    @Resource
    private DynamicMapper dynamicMapper;

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private WorkInfoMapper workInfoMapper;


}
