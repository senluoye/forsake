package com.qks.feignclient.service;

import com.qks.common.entity.po.Dynamic;
import com.qks.common.entity.po.RecordAudit;
import com.qks.feignclient.service.backImpl.DynamicBackImpl;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(value = "dynamicservice", fallback = DynamicBackImpl.class)
public interface MessageClient {


    List<Dynamic> selectList(List<RecordAudit> recordAuditList);
}
