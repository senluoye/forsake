package com.qks.userservice.service;

import com.qks.common.exception.ServiceException;
import com.qks.common.model.dto.LoginDTO;
import com.qks.common.model.dto.TokenDTO;
import com.qks.common.model.dto.UserDTO;
import com.qks.common.model.dto.UserInfoDTO;
import com.qks.common.model.po.user.User;
import com.qks.common.model.po.user.UserRoleRel;
import com.qks.common.model.vo.ResVO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-05 18:39
 */
public interface UserService {

    ResVO<TokenDTO> userLogin(LoginDTO loginDTO) throws ServiceException, IOException;

    ResVO<UserInfoDTO> userInfo(String token) throws ServiceException;

    ResVO<Map<String, Object>> addUserRole(UserRoleRel relations, String token) throws ServiceException;

    ResVO<Map<String, Object>> removeUserRole(UserRoleRel relations, String token) throws ServiceException;

    ResVO<Map<String, Object>> modifyUserInfo(String token, User user) throws ServiceException;

    ResVO<List<UserInfoDTO>> getAllUserInfo(String token, User user) throws ServiceException;

    ResVO<Map<String, Object>> deleteUser(String token, User user) throws ServiceException;

    ResVO<Map<String, Object>> addUser(String token, User user) throws ServiceException;

    ResVO<Map<String, Object>> modifyPassword(String token, User user) throws ServiceException;

    ResVO<Integer> userRegister(UserDTO user) throws ServiceException;
}
