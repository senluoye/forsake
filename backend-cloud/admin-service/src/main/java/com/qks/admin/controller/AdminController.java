package com.qks.admin.controller;

import com.qks.admin.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @ClassName AdminComtroller
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-30 19:08
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Resource
    private AdminService adminService;


}
