package com.qks.backuser.controller;

import com.qks.backend.entity.po.BackUser;
import com.qks.backend.entity.vo.AdminLoginVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.BackUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BackUserController
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-08 18:14
 */
@RestController
@RequestMapping("/api/admin/user")
public class BackUserController {

    private final BackUserService backUserService;

    public BackUserController(BackUserService backUserService) {
        this.backUserService = backUserService;
    }

    /**
     * 管理端人员登陆
     *
     * @param adminLoginVO
     * @return
     * @throws ServiceException
     */
    @PostMapping("/login")
    public ResVO<Map<String, Object>> adminLogin(@RequestBody AdminLoginVO adminLoginVO) throws ServiceException {
        return backUserService.adminLogin(adminLoginVO);
    }

    @GetMapping("/info")
    public ResVO<BackUser> getUserInfo(@RequestHeader("token") String token) throws ServiceException {
        return backUserService.getUserInfo(token);
    }

    /**
     * 管理员添加审核员
     *
     * @param token
     * @param backUser
     * @return
     * @throws ServiceException
     */
    @PostMapping("/auditor")
    public ResVO<Map<String, Object>> addAuditor(@RequestHeader("token") String token,
                                                 @RequestBody BackUser backUser) throws ServiceException {
        return backUserService.addAuditor(token, backUser);
    }

    /**
     * 管理员查看审核员列表
     *
     * @param token
     * @return
     * @throws ServiceException
     */
    @GetMapping("/auditor/list/{currentPage}")
    public ResVO<List<BackUser>> getAuditorList(@RequestHeader("token") String token,
                                                @PathVariable("currentPage") Integer currentPage) throws ServiceException {
        return backUserService.getAuditorList(token, currentPage);
    }

    /**
     * 管理员删除审核员
     *
     * @param token
     * @return
     * @throws ServiceException
     */
    @DeleteMapping("/auditor/{id}")
    public ResVO<Map<String, Object>> deleteAuditor(@RequestHeader("token") String token,
                                                    @PathVariable("id") Long userId) throws ServiceException {
        return backUserService.deleteAuditor(token, userId);
    }

}
