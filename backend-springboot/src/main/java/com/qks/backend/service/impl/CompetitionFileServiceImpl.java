package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.CompetitionFileMapper;
import com.qks.backend.entity.po.CompetitionFile;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.CompetitionFileService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 15998
 * @description 针对表【competition_file】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class CompetitionFileServiceImpl extends ServiceImpl<CompetitionFileMapper, CompetitionFile>
        implements CompetitionFileService {

}




