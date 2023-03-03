package com.qks.backend.controller.dynamic;

import com.qks.backend.entity.po.DynamicCollect;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.DynamicCollectService;
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
@RequestMapping("/api/dynamic/collect")
public class DynamicCollectController {

    @Resource
    private DynamicCollectService dynamicCollectService;

    @GetMapping("/list")
    public ResVO<PageVO<List<DynamicCollect>>> getDynamicCollectList(@RequestBody Integer currentPage) throws ServiceException {
        return dynamicCollectService.getDynamicCollectList(currentPage);
    }

    @GetMapping("/one/{id}")
    public ResVO<DynamicCollect> getDynamicCollectById(@PathVariable("id") String id) {
        return dynamicCollectService.getDynamicCollectById(id);
    }

    @DeleteMapping
    public ResVO<Map<String, Object>> deletcDynamicCollectById(@RequestBody DynamicCollect dynamicCollect) throws ServiceException {
        return dynamicCollectService.deleteDynamicCollectById(dynamicCollect);
    }

}
