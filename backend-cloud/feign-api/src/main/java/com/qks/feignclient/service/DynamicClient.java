package com.qks.feignclient.service;

import com.qks.common.entity.po.Dynamic;
import com.qks.common.entity.po.DynamicFile;
import com.qks.common.entity.po.RecordAudit;
import com.qks.feignclient.service.backImpl.DynamicBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "dynamic-service", fallback = DynamicBackImpl.class)
public interface DynamicClient {

    @PostMapping("/api/feign/dynamic/list")
    List<Dynamic> selectList(@RequestBody List<RecordAudit> recordAuditList);

    @PostMapping("/api/feign/dynamic/file/list")
    List<DynamicFile> selectFileListByDynamicId(@RequestBody Long dynamicId);
}
