package com.qks.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.user.dao.UserMapper;
import com.qks.user.dao.UserRoleRelMapper;
import com.qks.work.entity.enums.PageEnum;
import com.qks.work.entity.po.User;
import com.qks.work.entity.po.UserRoleRel;
import com.qks.work.entity.vo.LoginVO;
import com.qks.work.entity.vo.PageVO;
import com.qks.work.entity.vo.ResVO;
import com.qks.work.exception.ServiceException;
import com.qks.user.service.UserRoleRelService;
import com.qks.user.service.UserService;
import com.qks.work.utls.JwtUtil;
import com.qks.work.utls.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 15998
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final UserMapper userMapper;
    private final RestTemplate restTemplate;
    private final UserRoleRelService userRoleRelService;
    private final UserRoleRelMapper userRoleRelMapper;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.appId}")
    private String appId;

    public UserServiceImpl(UserMapper userMapper, RestTemplate restTemplate, UserRoleRelService userRoleRelService,
                           UserRoleRelMapper userRoleRelMapper) {
        this.userMapper = userMapper;
        this.restTemplate = restTemplate;
        this.userRoleRelService = userRoleRelService;
        this.userRoleRelMapper = userRoleRelMapper;
    }

    @Override
    public ResVO<Map<String, Object>> wxLogin(LoginVO loginVO) throws ServiceException {
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + appId
                + "&secret=" + secret
                + "&js_code=" + loginVO.getCode()
                + "&grant_type=authorization_code";

        //利用spring原生http请求工具对接口进行请求
        String jsonData = restTemplate.getForObject(url, String.class);
        JSONObject jsonObject = JSONObject.parseObject(jsonData);


        //这个判断是判断我们的请求是否出错，如果没有出错的话就能够拿到openid
        if (StringUtils.contains(jsonData, "errcode")) {
            throw new ServiceException("无法登录: " + jsonData);
        }

        String openId = jsonObject.getString("openid");
        String sessionKey = jsonObject.getString("session_key");

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("open_id", openId));
        if (user == null) {
            // 如果用户没有用 wx.login 登录过
            user = loginVO.getUser();
            user.setOpenId(openId);
            user.setSessionKey(sessionKey);
            userMapper.insert(user);
        }

        String token = JwtUtil.createTokenByUserId(user.getId());
        user.setToken(token);
        userMapper.updateById(user);

        return R.map("token", token);
    }

    @Override
    public ResVO<Map<String, Object>> updateUser(User user) throws ServiceException {
        User oldUser = userMapper.selectById(user.getId());
        if (oldUser == null) {
            throw new ServiceException("用户不存在");
        }

        oldUser.setNickName(user.getNickName() == null ? oldUser.getNickName() : user.getNickName());
        oldUser.setAge(user.getAge() == null ? oldUser.getAge() : user.getAge());
        oldUser.setEmail(user.getEmail() == null ? oldUser.getEmail() : user.getEmail());
        oldUser.setPhone(user.getPhone() == null ? oldUser.getPhone() : user.getPhone());
        userMapper.updateById(oldUser);

        Map<String, Object> data = new HashMap<>();
        data.put("userId", user.getId());
        return R.success(data);
    }

    @Override
    public ResVO<PageVO<List<User>>> getUserList(Integer currentPage) throws ServiceException {
        IPage<User> userIPage = new Page<>(currentPage, PageEnum.DefaultNum.getPageNum());
        userMapper.selectPage(userIPage, new QueryWrapper<User>().orderByDesc("create_at"));

        long total = userIPage.getTotal();
        if (currentPage != 0 && total < (currentPage + 1) * 10L) {
            throw new ServiceException("后面没有数据啦");
        }

        PageVO<List<User>> data = new PageVO<>();
        data.setTotal(total);
        data.setCurrent(currentPage.longValue());
        data.setData(userIPage.getRecords());

        return R.success(data);
    }

    @Override
    public ResVO<Map<String, Object>> deleteUser(String token, Long id) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        if (!userRoleRelService.isAdminOrAuditor(userId)) {
            throw new ServiceException("用户权限不不足");
        }

        userMapper.deleteById(id);
        userRoleRelMapper.delete(new QueryWrapper<UserRoleRel>().eq("user_id", id));

        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        return R.success(data);
    }

    @Override
    public ResVO<User> getMe(String token) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);

        User me = userMapper.selectById(userId);
        if (me == null) {
            throw new ServiceException("用户不存在");
        }
        me.setPassword(null);
        return R.success(me);
    }

}




