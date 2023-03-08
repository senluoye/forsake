package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.BrowserMapper;
import com.qks.backend.entity.po.browser.Browser;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.BrowserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName: BrowserServiceImpl
 * @Description
 * @Author: Administrator
 * @Version v1.0
 * @Create: 2023-03-08 15:54
 **/
@Service
@Transactional(rollbackFor = ServiceException.class)
public class BrowserServiceImpl extends ServiceImpl<BrowserMapper, Browser> implements BrowserService {

    @Resource
    private BrowserMapper browserMapper;
}
