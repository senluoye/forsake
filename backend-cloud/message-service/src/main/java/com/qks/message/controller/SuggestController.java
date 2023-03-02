package com.qks.message.controller;

import com.qks.work.entity.po.Suggestion;
import com.qks.work.entity.vo.PageVO;
import com.qks.work.entity.vo.ResVO;
import com.qks.work.exception.ServiceException;
import com.qks.work.service.SuggestionService;
import com.qks.work.service.UserRoleRelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName CompetitionCollectController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-15 13:03
 */
@RestController
@RequestMapping("/api/suggest")
public class SuggestController {

    @Resource
    private SuggestionService suggestionService;

    @Resource
    private UserRoleRelService userRoleRelService;

    /**
     * 运营/管理员 查看所有建议
     *
     * @param token token
     */
    @GetMapping("/list")
    public ResVO<PageVO<List<Suggestion>>> getAllSuggest(@RequestHeader("token") String token,
                                                         @RequestBody Integer page) throws ServiceException {
//        String userId = JwtUtil.parser(token).get("userId").toString();
//        if (!userRoleRelService.isAdminOrAuditor(userId)) {
//            throw new ServiceException("账号权限不足");
//        }

        return suggestionService.getAllSuggest(page);
    }

    @GetMapping("one/{id}")
    public void getOneSuggest(@PathVariable("id") String id) {
        System.out.println(id);
    }

    @PostMapping()
    public ResVO<Long> addSuggestion(@RequestHeader("token") String token, @RequestBody Suggestion suggestion) {
        return suggestionService.addSuggestion(suggestion);
    }
}
