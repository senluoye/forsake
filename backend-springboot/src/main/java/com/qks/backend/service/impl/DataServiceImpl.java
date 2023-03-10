package com.qks.backend.service.impl;

import com.qks.backend.entity.vo.OverviewVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.DataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName DataServiceImpol
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-10 17:23
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class DataServiceImpl implements DataService {
    @Override
    public ResVO<OverviewVO> getOverviewData(String token) {
        return null;
    }
}
