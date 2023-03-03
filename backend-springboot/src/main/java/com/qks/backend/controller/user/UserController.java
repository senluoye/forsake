package com.qks.backend.controller.user;

import com.qks.backend.entity.po.User;
import com.qks.backend.entity.vo.LoginVO;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CompetitionCollectComtroller
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-15 13:03
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户微信登录
     *
     * @param loginVO
     * @return
     * @throws ServiceException
     */
    @PostMapping("/login")
    public ResVO<Map<String, Object>> login(@RequestBody LoginVO loginVO) throws ServiceException {
        return userService.wxLogin(loginVO);
    }

    /**
     * 获取个人信息
     *
     * @param token
     * @return
     */
    @GetMapping("/info")
    public ResVO<User> getMe(@RequestHeader("token") String token) throws ServiceException {
        return userService.getMe(token);
    }

    @PutMapping("/info")
    public ResVO<Map<String, Object>> updateUser(@RequestBody User user) throws ServiceException {
        return userService.updateUser(user);
    }

    @GetMapping("/list")
    public ResVO<PageVO<List<User>>> getUserList(@RequestBody Integer currentPage) throws ServiceException {
        return userService.getUserList(currentPage);
    }

    @DeleteMapping("/{id}")
    public ResVO<Map<String, Object>> deleteUser(@PathVariable("id") Long id, @RequestHeader("token") String token) throws ServiceException {
        return userService.deleteUser(token, id);
    }
}
