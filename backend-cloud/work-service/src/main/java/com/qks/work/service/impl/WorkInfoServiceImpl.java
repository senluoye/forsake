package com.qks.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.user.dao.WorkInfoMapper;
import com.qks.work.entity.po.WorkInfo;
import com.qks.work.exception.ServiceException;
import com.qks.user.service.WorkInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 15998
 * @description 针对表【work_file】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class WorkInfoServiceImpl extends ServiceImpl<WorkInfoMapper, WorkInfo>
        implements WorkInfoService {

}