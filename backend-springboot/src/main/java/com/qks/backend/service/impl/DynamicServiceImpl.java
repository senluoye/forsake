package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.*;
import com.qks.backend.entity.enums.PageEnum;
import com.qks.backend.entity.enums.audit.AuditFlagEnum;
import com.qks.backend.entity.enums.audit.AuditStateEnum;
import com.qks.backend.entity.po.*;
import com.qks.backend.entity.vo.DynamicVO;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.DynamicService;
import com.qks.backend.utls.JwtUtil;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 15998
 * @description 针对表【dynamic】的数据库操作Service实现
 * @createDate 2023-01-15 12:51:17
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class DynamicServiceImpl extends ServiceImpl<DynamicMapper, Dynamic>
        implements DynamicService {

    @Resource
    private DynamicMapper dynamicMapper;

    @Resource
    private DynamicFileMapper dynamicFileMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DynamicCommentMapper dynamicCommentMapper;

    @Resource
    private DynamicStarMapper dynamicStarMapper;

    @Resource
    private DynamicCollectMapper dynamicCollectMapper;

    @Resource
    private RecordAuditMapper recordAuditMapper;

    @Override
    public ResVO<Dynamic> getDynamicById(Dynamic dynamic) {
        Dynamic data = dynamicMapper.selectById(dynamic.getId());
        return R.success(data);
    }

    @Override
    public ResVO<PageVO<List<DynamicVO>>> getDynamicList(String token, Long currentPage) throws ServiceException {
        long total = dynamicMapper.selectTotal(AuditFlagEnum.Dynamic.getFlag(), AuditStateEnum.Pass.getState());
        if (currentPage != 0 && total <= currentPage * PageEnum.DefaultNum.getPageNum()) {
            throw new ServiceException("后面没有数据啦");
        }

        List<Dynamic> dynamicList = dynamicMapper.selectDynamicList(
                AuditFlagEnum.Dynamic.getFlag(), AuditStateEnum.Pass.getState(), currentPage * 20
        );
        List<DynamicVO> dynamicVOList = new ArrayList<>();
        Long userId = JwtUtil.getUserId(token);

        for (Dynamic dynamic : dynamicList) {
            User user = userMapper.selectById(dynamic.getUserId());
            // 获取动态文件路径
            List<DynamicFile> dynamicFileList = dynamicFileMapper.selectList(
                    new QueryWrapper<DynamicFile>().eq("dynamic_id", dynamic.getId())
            );
            DynamicStar dynamicStar = dynamicStarMapper.selectOne(
                    new QueryWrapper<DynamicStar>()
                            .eq("dynamic_id", dynamic.getId())
                            .eq("user_id", userId)
            );
            DynamicCollect dynamicCollect = dynamicCollectMapper.selectOne(
                    new QueryWrapper<DynamicCollect>().eq("dynamic_id", dynamic.getId())
            );

            DynamicVO dynamicVO = new DynamicVO();
            dynamicVO.setId(dynamic.getId());
            dynamicVO.setTitle(dynamic.getTitle());
            dynamicVO.setCreateAt(dynamic.getCreateAt());
            dynamicVO.setUpdateAt(dynamic.getUpdateAt());
            dynamicVO.setContent(dynamic.getContent());
            dynamicVO.setLikeCount(dynamic.getLikeCount());
            dynamicVO.setCollectCount(dynamic.getCollectCount());
            dynamicVO.setNickName(user.getNickName());
            dynamicVO.setDynamicFileList(dynamicFileList);
            dynamicVO.setAvatarUrl(user.getAvatarUrl());
            dynamicVO.setCommentCount(dynamic.getCommentCount());
            dynamicVO.setIsLike(dynamicStar != null);
            dynamicVO.setIsCollect(dynamicCollect != null);

            dynamicVOList.add(dynamicVO);
        }

        PageVO<List<DynamicVO>> data = new PageVO<>();
        data.setTotal(total - 1);
        data.setCurrent(currentPage);
        data.setData(dynamicVOList);

        return R.success(data);
    }

    @Override
    public ResVO<Map<String, Object>> addDynamic(String token, DynamicVO dynamicVO) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }

        // 插入数据到学习表
        Dynamic dynamic = Dynamic.builder()
                .title(dynamicVO.getTitle())
                .content(dynamicVO.getContent())
                .userId(user.getId())
                .build();
        dynamicMapper.insert(dynamic);
        for (DynamicFile dynamicFile : dynamicVO.getDynamicFileList()) {
            dynamicFile.setDynamicId(dynamic.getId());
            dynamicFileMapper.insert(dynamicFile);
        }

        // 插入数据到审核表
        RecordAudit recordAudit = RecordAudit.builder()
                .recordId(dynamic.getId())
                .state(AuditStateEnum.UnCheck.getState())
                .flag(AuditFlagEnum.Dynamic.getFlag())
                .build();
        recordAuditMapper.insert(recordAudit);

        return R.map("dynamicId", dynamic.getId());
    }

    @Override
    public ResVO<Map<String, Object>> updateDynamic(Dynamic dynamic) throws ServiceException {
        Dynamic oldDynamic = dynamicMapper.selectById(dynamic.getId());
        if (oldDynamic == null) {
            throw new ServiceException("动态不存在");
        }

        int res = dynamicMapper.updateById(dynamic);
        if (res <= 0) {
            throw new ServiceException("修改动态失败");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("dynamicId", dynamic.getId());

        return R.success(data);
    }

    @Override
    public ResVO<Map<String, Object>> deleteDynamic(Dynamic dynamic) throws ServiceException {
        if (dynamicMapper.deleteById(dynamic.getId()) <= 0) {
            throw new ServiceException("要删除的动态不存在");
        }

        Map<String, Object> data = new HashMap<>();
        data.put("dynamicId", dynamic.getId());

        return R.success(data);
    }

    @Override
    public ResVO<Map<String, Object>> starDynamic(String token, DynamicVO dynamicVO) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        if (userMapper.selectById(userId) == null) {
            throw new ServiceException("用户不存在");
        }
        if (dynamicMapper.selectById(dynamicVO.getId()) == null) {
            throw new ServiceException("动态不存在");
        }

        DynamicStar dynamicStar = dynamicStarMapper.selectOne(
                new QueryWrapper<DynamicStar>().eq("dynamic_id", dynamicVO.getId())
        );
        if (dynamicStar == null) {
            dynamicStar = new DynamicStar();
            dynamicStar.setDynamicId(dynamicVO.getId());
            dynamicStar.setUserId(userId);
            dynamicStarMapper.insert(dynamicStar);
            dynamicMapper.updateLikeCount(dynamicVO.getId(), dynamicVO.getLikeCount() + 1);
        } else {
            dynamicStarMapper.deleteById(dynamicStar.getId());
            dynamicMapper.updateLikeCount(dynamicVO.getId(), dynamicVO.getLikeCount() - 1);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("dynamicStarId", dynamicStar.getId());
        return R.success(data);
    }

    @Override
    public ResVO<Map<String, Object>> collectDynamic(String token, DynamicVO dynamic) {
        return null;
    }
}




