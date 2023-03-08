package com.qks.backend.controller.borwser;

import com.qks.backend.entity.vo.ResVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/browser")
public class BrowserController {

    @PostMapping
    public ResVO<Map<String, Object>> addBrowser() {

    }


}
