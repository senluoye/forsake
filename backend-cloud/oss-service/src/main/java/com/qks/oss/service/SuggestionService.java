package com.qks.oss.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.common.entity.po.Suggestion;
import com.qks.common.entity.vo.PageVO;
import com.qks.common.entity.vo.ResVO;
import com.qks.common.exception.ServiceException;

import java.util.List;

/**
 * @author 15998
 * @description 针对表【suggestion】的数据库操作Service
 * @createDate 2023-01-15 12:51:17
 */
public interface SuggestionService extends IService<Suggestion> {

    ResVO<PageVO<List<Suggestion>>> getAllSuggest(Integer page) throws ServiceException;

    ResVO<Long> addSuggestion(Suggestion suggestion);
}
