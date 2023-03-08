package com.qks.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.backend.entity.po.Star;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.entity.vo.StarDynamicVO;

import java.util.List;

public interface StarService extends IService<Star> {
    ResVO<PageVO<List<StarDynamicVO>>> getStarList(Long userId, Long currentPage);
}
