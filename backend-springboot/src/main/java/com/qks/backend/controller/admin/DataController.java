package com.qks.backend.controller.admin;

import com.qks.backend.entity.vo.OverviewVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.service.DataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName DataController
 * @Description 数据展示
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-09 19:30
 */
@RestController
@RequestMapping("/api/data")
public class DataController {

    @Resource
    private DataService dataService;

    @GetMapping("/overview")
    public ResVO<OverviewVO> getOverviewData(@RequestHeader("token") String token) {
        return dataService.getOverviewData(token);
    }
}
