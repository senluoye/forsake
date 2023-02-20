package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.BackUserMapper;
import com.qks.backend.dao.BackUserRoleRelMapper;
import com.qks.backend.entity.enums.PageEnum;
import com.qks.backend.entity.po.BackUser;
import com.qks.backend.entity.po.BackUserRoleRel;
import com.qks.backend.entity.vo.AdminLoginVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.BackUserService;
import com.qks.backend.utls.BackUserUtil;
import com.qks.backend.utls.JwtUtil;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BackUserServiceImpl
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-02-08 18:15
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class BackUserServiceImpl extends ServiceImpl<BackUserMapper, BackUser> implements BackUserService {

    @Resource
    private BackUserMapper backUserMapper;

    @Resource
    private BackUserRoleRelMapper backUserRoleRelMapper;

    @Resource
    private BackUserUtil backUserUtil;

    @Override
    public ResVO<Map<String, Object>> adminLogin(AdminLoginVO adminLoginVO) throws ServiceException {
        BackUser admin = backUserMapper.selectOne(
                new LambdaQueryWrapper<BackUser>()
                        .eq(BackUser::getCode, adminLoginVO.getCode())
                        .eq(BackUser::getPassword, adminLoginVO.getPassword())
        );
        if (admin == null) {
            throw new ServiceException("用户不存在");
        }

        String token = JwtUtil.createTokenByUserId(admin.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        return R.success(data);
    }

    @Override
    public ResVO<Map<String, Object>> addAuditor(String token, BackUser backUser) throws ServiceException {
        if (!backUserUtil.isAdmin(token)) {
            throw new ServiceException("用户不是管理员");
        }

        if (backUserMapper.insert(backUser) < 1) {
            throw new ServiceException("增加用户失败");
        }

        BackUserRoleRel rel = new BackUserRoleRel(backUser.getId(), 2L);
        if (backUserRoleRelMapper.insert(rel) < 1) {
            throw new ServiceException("增加用户角色关系失败");
        }

        return R.map("user_id", backUser.getId());
    }

    @Override
    public ResVO<List<BackUser>> getAuditorList(String token, Integer currentPage) throws ServiceException {
        Page<BackUser> backUserPage = new Page<>(currentPage, PageEnum.DefaultNum.getPageNum());
        backUserMapper.selectPage(backUserPage, new QueryWrapper<BackUser>().orderByDesc("create_at"));

        long total = backUserPage.getTotal();
        if (currentPage != 0 && total <= currentPage * 20L) {
            throw new ServiceException("后面没有数据啦");
        }
        List<BackUser> backUsers = backUserPage.getRecords();
        return R.success(backUsers);
    }

    @Override
    public ResVO<Map<String, Object>> deleteAuditor(String token, Long userId) throws ServiceException {
        if (!backUserUtil.isAdmin(token)) {
            throw new ServiceException("用户不是管理员");
        }
        BackUserRoleRel rel = backUserRoleRelMapper.selectOne(
                new LambdaQueryWrapper<BackUserRoleRel>().eq(BackUserRoleRel::getBackUserId, userId)
        );
        if (rel == null) {
            throw new ServiceException("用户不存在");
        }

        // 删除用户和用户角色关系
        backUserMapper.deleteById(userId);
        backUserRoleRelMapper.delete(
                new LambdaQueryWrapper<BackUserRoleRel>().eq(BackUserRoleRel::getBackUserId, userId)
        );

        return R.map("userId", userId);
    }

    @Override
    public ResVO<BackUser> getUserInfo(String token) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        BackUser backUser = backUserMapper.selectById(userId);
        if (backUser == null) {
            throw new ServiceException("用户不存在");
        }
        backUser.setPassword(null);

        return R.success(backUser);
    }


}
