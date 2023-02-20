package com.qks.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.common.exception.ServiceException;
import com.qks.common.model.dto.*;
import com.qks.common.model.po.user.User;
import com.qks.common.model.po.user.UserRoleRel;
import com.qks.common.model.vo.ResVO;
import com.qks.common.utils.JwtUtils;
import com.qks.common.utils.Res;
import com.qks.userservice.mapper.UserMapper;
import com.qks.userservice.service.UserService;
import com.qks.userservice.utils.AuthUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-05 18:46
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public ResVO<TokenDTO> userLogin(LoginDTO loginDTO) throws ServiceException, IOException {
        String code = loginDTO.getCode();
        WxAuthDTO auth = AuthUtils.auth(code);
        String token = JwtUtils.createToken(auth);
        return Res.success(new TokenDTO(token));
    }

    @Override
    public ResVO<UserInfoDTO> userInfo(String token) throws ServiceException {
        return null;
    }

    @Override
    public ResVO<Map<String, Object>> addUserRole(UserRoleRel relations, String token) throws ServiceException {
        return null;
    }

    @Override
    public ResVO<Map<String, Object>> removeUserRole(UserRoleRel relations, String token) throws ServiceException {
        return null;
    }

    @Override
    public ResVO<Map<String, Object>> modifyUserInfo(String token, User user) throws ServiceException {
        return null;
    }

    @Override
    public ResVO<List<UserInfoDTO>> getAllUserInfo(String token, User user) throws ServiceException {
        return null;
    }

    @Override
    public ResVO<Map<String, Object>> deleteUser(String token, User user) throws ServiceException {
        return null;
    }

    @Override
    public ResVO<Map<String, Object>> addUser(String token, User user) throws ServiceException {
        return null;
    }

    @Override
    public ResVO<Map<String, Object>> modifyPassword(String token, User user) throws ServiceException {
        return null;
    }

    @Override
    public ResVO<Integer> userRegister(UserDTO user) throws ServiceException {
        return null;
    }
}
