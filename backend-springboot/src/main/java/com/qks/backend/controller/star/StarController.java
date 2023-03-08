package com.qks.backend.controller.star;

import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.entity.vo.StarDynamicVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.StarService;
import com.qks.backend.utls.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/star")
public class StarController {

    @Resource
    private StarService starService;

    @GetMapping("/list/{currentPage}")
    public ResVO<PageVO<List<StarDynamicVO>>> getStarList(@RequestHeader("token") String token,
                                                          @PathVariable("currentPage") Long currentPage) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        return starService.getStarList(userId, currentPage);
    }

}
