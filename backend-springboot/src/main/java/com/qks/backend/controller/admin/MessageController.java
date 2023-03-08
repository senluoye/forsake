package com.qks.backend.controller.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qks.backend.entity.po.message.Message;
import com.qks.backend.entity.vo.MessageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.MessageService;
import com.qks.backend.utls.JwtUtil;
import com.qks.backend.utls.R;
import org.springframework.web.bind.annotation.*;

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

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 管理员发送一条消息通知
     *
     * @param token
     * @param messageVO
     * @return
     * @throws ServiceException
     */
    @PostMapping
    public ResVO<Map<String, Object>> adminAddMessage(@RequestHeader("token") String token,
                                                      @RequestBody MessageVO messageVO) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        Message message = Message.builder()
                .userId(userId)
                .content(messageVO.getContent())
                .build();
        return messageService.adminAddMessage(message);
    }


    /**
     * 管理员发送一条消息通知
     *
     * @param token
     * @param messageVO
     * @return
     * @throws ServiceException
     */
    @PutMapping("/{id}")
    public ResVO<Map<String, Object>> adminDeleteMessage(@RequestHeader("token") String token,
                                                         @PathVariable Long id) throws ServiceException {
        messageService.remove(
                new LambdaQueryWrapper<Message>().eq(Message::getId, id)
        );
        return R.map("messageId", id);
    }
}
