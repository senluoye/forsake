package com.qks.feignclient.service.backImpl;

import com.qks.common.entity.po.Dynamic;
import com.qks.common.entity.po.DynamicFile;
import com.qks.common.entity.po.RecordAudit;
import com.qks.feignclient.service.DynamicClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DynamicBackImpl implements DynamicClient {
    @Override
    public List<Dynamic> selectList(List<RecordAudit> recordAuditList) {
        return null;
    }

    @Override
    public List<DynamicFile> selectFileListByDynamicId(Long dynamicId) {
        return null;
    }
}
