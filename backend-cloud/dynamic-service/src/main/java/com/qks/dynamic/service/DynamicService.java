package com.qks.dynamic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.common.entity.po.Dynamic;
import com.qks.common.entity.vo.DynamicVO;
import com.qks.common.entity.vo.FrontendDynamicItemVO;
import com.qks.common.entity.vo.PageVO;
import com.qks.common.entity.vo.ResVO;
import com.qks.common.exception.ServiceException;

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

    ResVO<DynamicVO> getDynamicById(String dynamicId);

    ResVO<PageVO<List<FrontendDynamicItemVO>>> getDynamicList(String token, Long currentPage) throws ServiceException;

    ResVO<Map<String, Object>> deleteDynamic(Dynamic dynamic) throws ServiceException;

    ResVO<Map<String, Object>> starDynamic(String token, DynamicVO dynamic) throws ServiceException;

    ResVO<Map<String, Object>> collectDynamic(String token, DynamicVO dynamic);
}
