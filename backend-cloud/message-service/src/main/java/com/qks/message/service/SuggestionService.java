package com.qks.message.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.common.entity.po.Notice;
import com.qks.common.entity.po.Suggestion;
import com.qks.common.entity.vo.PageVO;
import com.qks.common.entity.vo.ResVO;
import com.qks.common.exception.ServiceException;

import java.util.List;

public interface SuggestionService extends IService<Suggestion> {

    ResVO<PageVO<List<Suggestion>>> getAllSuggest(Integer page) throws ServiceException;

    ResVO<Long> addSuggestion(Suggestion suggestion);
}
