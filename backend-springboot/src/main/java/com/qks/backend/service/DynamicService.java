package com.qks.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.backend.entity.po.Dynamic;
import com.qks.backend.entity.vo.DynamicVO;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @author 15998
 * @description 针对表【dynamic】的数据库操作Service
 * @createDate 2023-01-15 12:51:17
 */
public interface DynamicService extends IService<Dynamic> {

    ResVO<Map<String, Object>> addDynamic(String token, DynamicVO dynamicVO) throws ServiceException;

    ResVO<Map<String, Object>> updateDynamic(Dynamic dynamic) throws ServiceException;

    ResVO<Dynamic> getDynamicById(Dynamic dynamic);

    ResVO<PageVO<List<DynamicVO>>> getDynamicList(String token, Long currentPage) throws ServiceException;

    ResVO<Map<String, Object>> deleteDynamic(Dynamic dynamic) throws ServiceException;

    ResVO<Map<String, Object>> starDynamic(String token, DynamicVO dynamic) throws ServiceException;

    ResVO<Map<String, Object>> collectDynamic(String token, DynamicVO dynamic);
}
