package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.WorkCommentMapper;
import com.qks.backend.entity.po.WorkComment;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.WorkCommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 15998
 * @description 针对表【work_comment】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class WorkCommentServiceImpl extends ServiceImpl<WorkCommentMapper, WorkComment>
        implements WorkCommentService {

}




