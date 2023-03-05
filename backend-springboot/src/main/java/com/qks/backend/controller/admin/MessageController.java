package com.qks.backend.controller.admin;

import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName MessageController
 * @Description 消息与投诉
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-08 23:07
 */
@RestController
@RequestMapping("/api/message")
public class MessageController {

//    private final MessageService

    /**
     * 管理员发送一条官方消息
     *
     * @param token
     * @return
     * @throws ServiceException
     */
    @PostMapping
    public ResVO<Map<String, Object>> adminAddMessage(@RequestHeader("token") String token) throws ServiceException {
        return null;
    }
}
