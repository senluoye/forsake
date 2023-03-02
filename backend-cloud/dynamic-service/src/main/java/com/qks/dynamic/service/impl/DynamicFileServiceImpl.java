package com.qks.dynamic.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.dynamic.dao.DynamicFileMapper;
import com.qks.common.entity.po.DynamicFile;
import com.qks.common.exception.ServiceException;
import com.qks.dynamic.service.DynamicFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 15998
 * @description 针对表【dynamic_file】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class DynamicFileServiceImpl extends ServiceImpl<DynamicFileMapper, DynamicFile>
        implements DynamicFileService {

}




