package com.qks.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.work.dao.WorkCollectMapper;
import com.qks.common.entity.po.WorkCollect;
import com.qks.common.exception.ServiceException;
import com.qks.work.service.WorkCollectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 15998
 * @description 针对表【work_collect】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class WorkCollectServiceImpl extends ServiceImpl<WorkCollectMapper, WorkCollect>
        implements WorkCollectService {

}




