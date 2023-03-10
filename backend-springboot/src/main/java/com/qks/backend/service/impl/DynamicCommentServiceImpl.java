package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.DynamicCommentMapper;
import com.qks.backend.dao.DynamicMapper;
import com.qks.backend.dao.UserMapper;
import com.qks.backend.entity.enums.PageEnum;
import com.qks.backend.entity.po.DynamicComment;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.DynamicCommentService;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author 15998
 * @description 针对表【dynamic_comment】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class DynamicCommentServiceImpl extends ServiceImpl<DynamicCommentMapper, DynamicComment>
        implements DynamicCommentService {

    @Resource
    private DynamicCommentMapper dynamicCommentMapper;

    @Resource
    private DynamicMapper dynamicMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public ResVO<PageVO<List<DynamicComment>>> getDynamicCommentList(String dynamicId, Integer currentPage) throws ServiceException {
        if (dynamicMapper.selectById(dynamicId) == null) {
            throw new ServiceException("动态不存在");
        }

        IPage<DynamicComment> dynamicCommentIPage = new Page<>(currentPage, PageEnum.DefaultNum.getPageNum());
        dynamicCommentMapper.selectPage(dynamicCommentIPage,
                new QueryWrapper<DynamicComment>().eq("dynamic_id", dynamicId).orderByDesc("create_at"));

        long total = dynamicCommentIPage.getTotal();
        if (currentPage != 0 && total < (currentPage + 1) * 10L) {
            throw new ServiceException("后面没有数据啦");
        }

        PageVO<List<DynamicComment>> data = new PageVO<>();
        data.setTotal(total);
        data.setCurrent(currentPage.longValue());
        data.setData(dynamicCommentIPage.getRecords());

        return R.success(data);
    }

    @Override
    public ResVO<PageVO<List<DynamicComment>>> getSubDynamicComment(String parentId, Integer currentPage) throws ServiceException {
        if (dynamicCommentMapper.selectById(parentId) == null) {
            throw new ServiceException("评论不存在");
        }

        IPage<DynamicComment> dynamicCommentIPage = new Page<>(currentPage, PageEnum.DefaultNum.getPageNum());
        dynamicCommentMapper.selectPage(dynamicCommentIPage,
                new QueryWrapper<DynamicComment>().eq("parent_id", parentId).orderByDesc("create_at"));

        long total = dynamicCommentIPage.getTotal();
        if (currentPage != 0 && total < (currentPage + 1) * 10L) {
            throw new ServiceException("后面没有数据啦");
        }

        PageVO<List<DynamicComment>> data = new PageVO<>();
        data.setTotal(total);
        data.setCurrent(currentPage.longValue());
        data.setData(dynamicCommentIPage.getRecords());

        return R.success(data);
    }

    @Override
    public ResVO<Map<String, Object>> addComment(DynamicComment dynamicComment) throws ServiceException {
        checkDynamicComment(dynamicComment);
        dynamicCommentMapper.insert(dynamicComment);
        return R.map("dynamicCommentId", dynamicComment.getId());
    }

    @Override
    public ResVO<Map<String, Object>> deleteComment(DynamicComment dynamicComment) {
        dynamicCommentMapper.deleteById(dynamicComment);
        return R.map("dynamicCommentId", dynamicComment.getId());
    }

    private void checkDynamicComment(DynamicComment dynamicComment) throws ServiceException {
        Long dynamicId = dynamicComment.getDynamicId();
        Long userId = dynamicComment.getUserId();
        Long parentCommentId = dynamicComment.getParentCommentId();

        if (dynamicMapper.selectById(dynamicId) == null) {
            throw new ServiceException("动态不存在");
        }

        if (userMapper.selectById(userId) == null) {
            throw new ServiceException("用户不存在");
        }

        if (parentCommentId != null && dynamicCommentMapper.selectById(parentCommentId) == null) {
            throw new ServiceException("选择的评论不存在");
        }
    }
}




