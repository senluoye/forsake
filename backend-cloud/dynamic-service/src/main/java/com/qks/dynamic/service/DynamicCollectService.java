package com.qks.dynamic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.common.entity.po.DynamicCollect;
import com.qks.common.entity.vo.PageVO;
import com.qks.common.entity.vo.ResVO;
import com.qks.common.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @author 15998
 * @description 针对表【dynamic_collect】的数据库操作Service
 * @createDate 2023-01-15 12:51:17
 */
public interface DynamicCollectService extends IService<DynamicCollect> {

    ResVO<PageVO<List<DynamicCollect>>> getDynamicCollectList(Integer currentPage) throws ServiceException;

    ResVO<DynamicCollect> getDynamicCollectById(String id);

    ResVO<Map<String, Object>> deleteDynamicCollectById(DynamicCollect dynamicCollect) throws ServiceException;
}
