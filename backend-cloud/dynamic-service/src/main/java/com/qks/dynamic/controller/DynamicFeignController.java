package com.qks.dynamic.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qks.dynamic.service.DynamicFileService;
import com.qks.dynamic.service.DynamicService;
import com.qks.common.entity.po.Dynamic;
import com.qks.common.entity.po.DynamicFile;
import com.qks.common.entity.po.RecordAudit;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController("/api/feign/dynamic")
public class DynamicFeignController {

    @Resource
    private DynamicService dynamicService;

    @Resource
    private DynamicFileService dynamicFileService;

    @PostMapping("/list")
    public List<Dynamic> selectList(@RequestBody List<RecordAudit> recordAuditList) {
        return dynamicService.list(new LambdaQueryWrapper<Dynamic>().in(
                Dynamic::getId,
                recordAuditList.stream()
                        .map(RecordAudit::getRecordId)
                        .collect(Collectors.toList())
        ));
    }

    @PostMapping("/file/list")
    List<DynamicFile> selectFileListByDynamicId(@RequestBody Integer dynamicId) {
        return dynamicFileService.list(
                new LambdaQueryWrapper<DynamicFile>().eq(DynamicFile::getDynamicId, dynamicId)
        );
    }
}
