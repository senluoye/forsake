package com.qks.userservice.controller;

import com.qks.common.exception.ServiceException;
import com.qks.common.model.dto.LoginDTO;
import com.qks.common.model.dto.TokenDTO;
import com.qks.common.model.dto.UserDTO;
import com.qks.common.model.dto.UserInfoDTO;
import com.qks.common.model.po.user.User;
import com.qks.common.model.po.user.UserRoleRel;
import com.qks.common.model.vo.ResVO;
import com.qks.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-05 18:36
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户登陆
     *
     * @param user 用户数据
     * @return
     * @throws ServiceException
     */
    @PostMapping("/login")
    public ResVO<TokenDTO> userLogin(@RequestBody LoginDTO loginDTO) throws ServiceException, IOException {
        return userService.userLogin(loginDTO);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     * @throws ServiceException
     */
    @PostMapping("/register")
    public ResVO<Integer> userRegister(@RequestBody UserDTO user) throws ServiceException {
        return userService.userRegister(user);
    }

    /**
     * 获取用户信息
     *
     * @return
     * @throws ServiceException
     */
    @GetMapping("/info")
    public ResVO<UserInfoDTO> userInfo(@RequestHeader("token") String token) throws ServiceException {
        return userService.userInfo(token);
    }

    /**
     * 给予用户角色
     *
     * @param relations
     * @param token
     * @return
     * @throws ServiceException
     */
    @PostMapping("/authorize")
    public ResVO<Map<String, Object>> addUserRole(@RequestBody UserRoleRel relations,
                                                  @RequestHeader("token") String token) throws ServiceException {
        return userService.addUserRole(relations, token);
    }

    /**
     * 移除用户角色
     *
     * @param relations
     * @param token
     * @return
     * @throws ServiceException
     */
    @PostMapping("/revoke-authorize")
    public ResVO<Map<String, Object>> removeUserRole(@RequestBody UserRoleRel relations,
                                                     @RequestHeader("token") String token) throws ServiceException {
        return userService.removeUserRole(relations, token);
    }

    /**
     * 修改用户信息
     *
     * @param token
     * @param user
     * @return
     * @throws ServiceException
     */
    @PutMapping("/info")
    public ResVO<Map<String, Object>> modifyUserInfo(@RequestHeader("token") String token,
                                                     @RequestBody User user) throws ServiceException {
        return userService.modifyUserInfo(token, user);
    }

    /**
     * 获取所有账号
     *
     * @param token
     * @param user
     * @return
     * @throws ServiceException
     */
    @PostMapping("/info/list")
    public ResVO<List<UserInfoDTO>> getAllUserInfo(@RequestHeader("token") String token,
                                                   @RequestBody User user) throws ServiceException {
        return userService.getAllUserInfo(token, user);
    }

    /**
     * 删除账号
     *
     * @param token
     * @param user
     * @return
     * @throws ServiceException
     */
    @PostMapping("/delete-user")
    public ResVO<Map<String, Object>> deleteUser(@RequestHeader("token") String token,
                                                 @RequestBody User user) throws ServiceException {
        return userService.deleteUser(token, user);
    }

    /**
     * 增加用户
     *
     * @param user
     * @param token
     * @return
     * @throws ServiceException
     */
    @PostMapping("/add")
    public ResVO<Map<String, Object>> addUser(@RequestBody User user,
                                              @RequestHeader("token") String token) throws ServiceException {
        return userService.addUser(token, user);
    }

    /**
     * 一键修改用户密码
     *
     * @param token
     * @param user
     * @return
     * @throws ServiceException
     */
    @PostMapping("/info/password")
    public ResVO<Map<String, Object>> modifyPassword(@RequestHeader("token") String token,
                                                     @RequestBody User user) throws ServiceException {
        return userService.modifyPassword(token, user);
    }

}
