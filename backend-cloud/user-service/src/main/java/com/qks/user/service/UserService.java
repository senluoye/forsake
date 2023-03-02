package com.qks.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qks.work.entity.po.User;
import com.qks.work.entity.vo.LoginVO;
import com.qks.work.entity.vo.PageVO;
import com.qks.work.entity.vo.ResVO;
import com.qks.work.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
 * @author 15998
 * @description 针对表【user】的数据库操作Service
 * @createDate 2023-01-15 12:51:17
 */
public interface UserService extends IService<User> {

    ResVO<Map<String, Object>> wxLogin(LoginVO loginVO) throws ServiceException;

    ResVO<Map<String, Object>> updateUser(User user) throws ServiceException;

    ResVO<PageVO<List<User>>> getUserList(Integer currentPage) throws ServiceException;

    ResVO<Map<String, Object>> deleteUser(String token, Long id) throws ServiceException;

    ResVO<User> getMe(String token) throws ServiceException;
}
