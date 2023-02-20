package com.qks.backend.service;

import com.qks.backend.entity.vo.OverviewVO;
import com.qks.backend.entity.vo.ResVO;

/**
 * @ClassName DataService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-10 17:23
 */
public interface DataService {
    ResVO<OverviewVO> getOverviewData(String token);
}
