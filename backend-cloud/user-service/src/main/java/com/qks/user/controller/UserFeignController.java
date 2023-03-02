package com.qks.user.controller;

import com.qks.common.entity.po.User;
import com.qks.user.dao.UserMapper;
import com.qks.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserFeignController {

    @Resource
    private UserService userService;


    @PostMapping("/api/feign/user")
    User selectById(@RequestBody Long userId) {
        return userService.getById(userId);
    }

}
