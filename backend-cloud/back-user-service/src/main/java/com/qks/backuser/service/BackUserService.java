package com.qks.backuser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.backend.entity.po.BackUser;
import com.qks.backend.entity.vo.AdminLoginVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @ClassName BackUserService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-08 18:15
 */
public interface BackUserService extends IService<BackUser> {
    ResVO<Map<String, Object>> adminLogin(AdminLoginVO adminLoginVO) throws ServiceException;

    ResVO<Map<String, Object>> addAuditor(String token, BackUser backUser) throws ServiceException;

    ResVO<List<BackUser>> getAuditorList(String token, Integer currentPage) throws ServiceException;

    ResVO<Map<String, Object>> deleteAuditor(String token, Long userId) throws ServiceException;

    ResVO<BackUser> getUserInfo(String token) throws ServiceException;
}
