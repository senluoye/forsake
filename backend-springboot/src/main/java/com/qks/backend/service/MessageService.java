package com.qks.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.backend.entity.po.message.Message;
import com.qks.backend.entity.vo.ResVO;

import java.util.Map;

public interface MessageService extends IService<Message> {

    ResVO<Map<String, Object>> adminAddMessage(Message message);
}
