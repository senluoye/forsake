package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.DynamicCollectMapper;
import com.qks.backend.entity.enums.PageEnum;
import com.qks.backend.entity.po.DynamicCollect;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.DynamicCollectService;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 15998
 * @description 针对表【dynamic_collect】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class DynamicCollectServiceImpl extends ServiceImpl<DynamicCollectMapper, DynamicCollect>
        implements DynamicCollectService {

    @Resource
    private DynamicCollectMapper dynamicCollectMapper;

    @Override
    public ResVO<PageVO<List<DynamicCollect>>> getDynamicCollectList(Integer currentPage) throws ServiceException {
        IPage<DynamicCollect> dynamicCollectIPage = new Page<>(currentPage, PageEnum.DefaultNum.getPageNum());
        dynamicCollectMapper.selectPage(dynamicCollectIPage, new QueryWrapper<DynamicCollect>().orderByDesc("create_at"));

        long total = dynamicCollectIPage.getTotal();
        if (currentPage != 0 && total < (currentPage + 1) * 10L) {
            throw new ServiceException("后面没有数据啦");
        }

        PageVO<List<DynamicCollect>> data = new PageVO<>();
        data.setTotal(total);
        data.setCurrent(currentPage.longValue());
        data.setData(dynamicCollectIPage.getRecords());

        return R.success(data);
    }

    @Override
    public ResVO<DynamicCollect> getDynamicCollectById(String id) {
        return null;
    }

    @Override
    public ResVO<Map<String, Object>> deleteDynamicCollectById(DynamicCollect dynamicCollect) throws ServiceException {
        if (dynamicCollectMapper.deleteById(dynamicCollect.getId()) <= 0) {
            throw new ServiceException("收藏记录不存在");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("dynamicCollectId", dynamicCollect.getId());
        return R.success(data);
    }
}




