package com.qks.backend.controller;

import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.service.OssService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OssController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-27 21:01
 */
@RestController
@RequestMapping("/api/oss")
@CrossOrigin    //解决跨域
public class OssController {

    @Resource
    private OssService ossService;

    //上传头像到OSS
    @PostMapping("/dynamic")
    public ResVO<List<String>> uploadDynamicFile(@RequestParam("file") List<MultipartFile> file) {
        return ossService.uploadDynamicFile(file);
    }

    @PostMapping("/competition")
    public ResVO<List<String>> uploadCompetitionFile(@RequestParam("file") List<MultipartFile> file) {
        return ossService.uploadCompetitionFile(file);
    }

    @PostMapping("/work")
    public ResVO<List<String>> uploadWorkFile(@RequestParam("file") List<MultipartFile> file) {
        return ossService.uploadWorkFile(file);
    }

    @GetMapping("/policy")
    public ResVO<Map<String, String>> policy() {
        return ossService.policy();
    }
}
