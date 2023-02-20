package com.qks.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description 测试接口
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-15 16:34
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "宁已经连上了";
    }
}
