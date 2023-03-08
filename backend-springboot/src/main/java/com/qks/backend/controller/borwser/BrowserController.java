package com.qks.backend.controller.borwser;

import com.qks.backend.entity.vo.BrowserContentVO;
import com.qks.backend.entity.vo.BrowserVO;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.BrowserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/browser")
public class BrowserController {

    @Resource
    private BrowserService browserService;

    /**
     * 增加一条浏览记录
     *
     * @param token
     * @param browserVO
     * @return
     * @throws ServiceException
     */
    @PostMapping
    public ResVO<Map<String, Object>> addBrowser(@RequestHeader("token") String token,
                                                 @RequestBody BrowserVO browserVO) throws ServiceException {
        return browserService.addBrowser(token, browserVO);
    }

    /**
     * 删除一条浏览记录
     *
     * @param token
     * @param browserVO
     * @return
     * @throws ServiceException
     */
    @PutMapping("/{id}")
    public ResVO<Map<String, Object>> deleteBrowser(@RequestHeader("token") String token,
                                                    @PathVariable("id") Long browserId) throws ServiceException {
        return browserService.deleteBrowser(token, browserId);
    }

    /**
     * 获取浏览记录
     *
     * @param token
     * @return
     * @throws ServiceException
     */
    @GetMapping("/list/{currentPage}")
    public ResVO<PageVO<List<BrowserContentVO>>> getBrowserList(@RequestHeader("token") String token,
                                                                @PathVariable("currentPage") Long currentPage) throws ServiceException {
        return browserService.getBrowserList(token, currentPage);
    }

}
