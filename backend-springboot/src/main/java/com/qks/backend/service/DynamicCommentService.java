package com.qks.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.backend.entity.po.DynamicComment;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @author 15998
 * @description 针对表【dynamic_comment】的数据库操作Service
 * @createDate 2023-01-15 12:51:17
 */
public interface DynamicCommentService extends IService<DynamicComment> {

    ResVO<PageVO<List<DynamicComment>>> getDynamicCommentList(String dynamicId, Integer currentPage) throws ServiceException;

    ResVO<PageVO<List<DynamicComment>>> getSubDynamicComment(String parentId, Integer currentPage) throws ServiceException;

    ResVO<Map<String, Object>> addComment(DynamicComment dynamicComment) throws ServiceException;


    ResVO<Map<String, Object>> deleteComment(DynamicComment dynamicComment);
}
