package com.qks.work.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.work.dao.WorkCommentMapper;
import com.qks.common.entity.po.WorkComment;
import com.qks.common.exception.ServiceException;
import com.qks.work.service.WorkCommentService;
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




