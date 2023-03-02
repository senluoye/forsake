package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.user.dao.SuggestionMapper;
import com.qks.backend.entity.po.Suggestion;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.user.service.SuggestionService;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 15998
 * @description 针对表【suggestion】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class SuggestionServiceImpl extends ServiceImpl<SuggestionMapper, Suggestion>
        implements SuggestionService {

    @Resource
    private SuggestionMapper suggestionMapper;

    @Override
    public ResVO<PageVO<List<Suggestion>>> getAllSuggest(Integer currentPage) throws ServiceException {
        IPage<Suggestion> suggestionIPage = new Page<>(currentPage, 10);
        suggestionMapper.selectPage(suggestionIPage, null);

        Long total = suggestionIPage.getTotal();
        if (currentPage != 0 && total < (currentPage + 1) * 10) {
            throw new ServiceException("后面没有数据啦");
        }

        PageVO<List<Suggestion>> pageVo = new PageVO<>();
        pageVo.setSize(10L);
        pageVo.setTotal(total);
        pageVo.setCurrent(currentPage.longValue());
        pageVo.setData(suggestionIPage.getRecords());

        return R.success(pageVo);
    }

    @Override
    public ResVO<Long> addSuggestion(Suggestion suggestion) {
        suggestionMapper.insert(suggestion);
        return R.success(suggestion.getId());
    }
}




