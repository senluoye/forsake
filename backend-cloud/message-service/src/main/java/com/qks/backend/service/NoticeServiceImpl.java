package com.qks.backend.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.user.dao.NoticeMapper;
import com.qks.backend.entity.po.Notice;
import com.qks.backend.exception.ServiceException;
import com.qks.user.service.NoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 15998
 * @description 针对表【notice】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
        implements NoticeService {

}




