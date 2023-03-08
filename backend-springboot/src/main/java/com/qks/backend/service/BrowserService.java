package com.qks.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.backend.entity.po.browser.Browser;
import com.qks.backend.entity.vo.BrowserContentVO;
import com.qks.backend.entity.vo.BrowserVO;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: BrowserService
 * @Description
 * @Author: Administrator
 * @Version v1.0
 * @Create: 2023-03-08 15:53
 **/
public interface BrowserService extends IService<Browser> {
    ResVO<Map<String, Object>> addBrowser(String token, BrowserVO browserVO) throws ServiceException;

    ResVO<Map<String, Object>> deleteBrowser(String token, Long browserId) throws ServiceException;

    ResVO<PageVO<List<BrowserContentVO>>> getBrowserList(String token, Long currentPage);
}
