package com.qks.backend.controller.dynamic;

import com.qks.backend.entity.po.Dynamic;
import com.qks.backend.entity.vo.DynamicVO;
import com.qks.backend.entity.vo.FrontendDynamicItemVO;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.DynamicService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CompetitionCollectComtroller
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-15 13:03
 */
@RestController
@RequestMapping("/api/dynamic")
public class DynamicController {

    @Resource
    private DynamicService dynamicService;

    /**
     * 根据id获取动态
     *
     * @param dynamic
     * @return
     */
    @GetMapping("/one/{id}")
    public ResVO<DynamicVO> getDynamicById(@PathVariable("id") String dynamicId) {
        return dynamicService.getDynamicById(dynamicId);
    }

    /**
     * 分页获取动态
     *
     * @param currentPage
     * @return
     * @throws ServiceException
     */
    @GetMapping("/list/{currentPage}")
    public ResVO<PageVO<List<FrontendDynamicItemVO>>> getDynamicList(@RequestHeader("token") String token, @PathVariable("currentPage") Integer currentPage) throws ServiceException {
        return dynamicService.getDynamicList(token, Long.valueOf(currentPage));
    }

    /**
     * 发布动态
     *
     * @param dynamicVO
     * @param token
     * @return
     * @throws ServiceException
     */
    @PostMapping
    public ResVO<Map<String, Object>> addDynamic(@RequestBody DynamicVO dynamicVO,
                                                 @RequestHeader("token") String token) throws ServiceException {
        return dynamicService.addDynamic(token, dynamicVO);
    }

    /**
     * 更新动态信息
     *
     * @param dynamic
     * @return
     * @throws ServiceException
     */
    @PutMapping
    public ResVO<Map<String, Object>> updateDynamic(@RequestBody Dynamic dynamic) throws ServiceException {
        System.out.println(dynamicService.list());
        return dynamicService.updateDynamic(dynamic);
    }

    /**
     * 删除动态
     *
     * @param dynamic
     * @return
     * @throws ServiceException
     */
    @DeleteMapping
    public ResVO<Map<String, Object>> deleteDynamic(@RequestBody Dynamic dynamic) throws ServiceException {
        return dynamicService.deleteDynamic(dynamic);
    }

    /**
     * 点赞/取消点赞
     *
     * @param token
     * @param dynamic
     * @return
     */
    @PostMapping("/star")
    public ResVO<Dynamic> starDynamic(@RequestHeader("token") String token, @RequestBody Long dynamicId)
            throws ServiceException {
        return dynamicService.starDynamic(token, dynamicId);
    }

    /**
     * 收藏/取消收藏
     *
     * @param token
     * @param dynamic
     * @return
     */
    @PostMapping("/collect")
    public ResVO<Map<String, Object>> collectDynamic(@RequestHeader("token") String token, @RequestBody DynamicVO dynamicVO)
            throws ServiceException {
        return dynamicService.collectDynamic(token, dynamicVO);
    }

}
