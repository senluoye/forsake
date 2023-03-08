package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.MessageMapper;
import com.qks.backend.entity.po.message.Message;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.service.MessageService;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements MessageService {

    @Resource
    private MessageMapper messageMapper;


    @Override
    public ResVO<Map<String, Object>> adminAddMessage(Message message) {
        messageMapper.insert(message);
        return R.map("messageId", message.getId());
    }
}
