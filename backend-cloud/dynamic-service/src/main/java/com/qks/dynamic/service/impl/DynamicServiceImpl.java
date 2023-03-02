package com.qks.dynamic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.common.entity.enums.PageEnum;
import com.qks.common.entity.enums.audit.AuditFlagEnum;
import com.qks.common.entity.enums.audit.AuditStateEnum;
import com.qks.common.entity.po.*;
import com.qks.common.entity.vo.DynamicVO;
import com.qks.common.entity.vo.FrontendDynamicItemVO;
import com.qks.common.entity.vo.PageVO;
import com.qks.common.entity.vo.ResVO;
import com.qks.common.exception.ServiceException;
import com.qks.dynamic.dao.*;
import com.qks.dynamic.service.DynamicService;
import com.qks.common.utils.JwtUtil;
import com.qks.common.helper.R;
import com.qks.feignclient.service.BackAuditClient;
import com.qks.feignclient.service.UserClient;
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
    private UserClient userClient;

    @Resource
    private DynamicStarMapper dynamicStarMapper;

    @Resource
    private DynamicCollectMapper dynamicCollectMapper;

    @Resource
    private BackAuditClient backAuditClient;

    @Override
    public ResVO<DynamicVO> getDynamicById(String dynamicId) {
        Dynamic dynamic = dynamicMapper.selectById(dynamicId);
        User user = userClient.selectById(dynamic.getUserId());
        // 获取动态文件路径
        List<DynamicFile> dynamicFileList = dynamicFileMapper.selectList(
                new LambdaQueryWrapper<DynamicFile>().eq(DynamicFile::getDynamicId, dynamic.getId())
        );
        DynamicStar dynamicStar = dynamicStarMapper.selectOne(
                new LambdaQueryWrapper<DynamicStar>()
                        .eq(DynamicStar::getDynamicId, dynamic.getId())
                        .eq(DynamicStar::getUserId, user.getId())
        );
        DynamicCollect dynamicCollect = dynamicCollectMapper.selectOne(
                new LambdaQueryWrapper<DynamicCollect>().eq(DynamicCollect::getDynamicId, dynamic.getId())
        );

        DynamicVO data = initDynamicVO(dynamic, user, dynamicFileList, dynamicStar, dynamicCollect);

        return R.success(data);
    }


    @Override
    public ResVO<PageVO<List<FrontendDynamicItemVO>>> getDynamicList(String token, Long currentPage) throws ServiceException {
        long total = dynamicMapper.selectTotal(AuditFlagEnum.Dynamic.getFlag(), AuditStateEnum.Pass.getState());
        if (currentPage != 0 && total <= currentPage * PageEnum.DefaultNum.getPageNum()) {
            throw new ServiceException("后面没有数据啦");
        }

        // 动态列表
        List<Dynamic> dynamicList = dynamicMapper.selectDynamicList(
                AuditFlagEnum.Dynamic.getFlag(), AuditStateEnum.Pass.getState(), currentPage * 20
        );

        // 前端vo
        List<FrontendDynamicItemVO> dynamicVOList = new ArrayList<>();

        Long userId = JwtUtil.getUserId(token);

        for (Dynamic dynamic : dynamicList) {
            User user = userClient.selectById(dynamic.getUserId());

            // 获取动态文件路径
            List<DynamicFile> dynamicFileList = dynamicFileMapper.selectList(
                    new QueryWrapper<DynamicFile>().eq("dynamic_id", dynamic.getId())
            );

            // 点赞情况
            DynamicStar dynamicStar = dynamicStarMapper.selectOne(
                    new QueryWrapper<DynamicStar>()
                            .eq("dynamic_id", dynamic.getId())
                            .eq("user_id", userId)
            );

            // 收藏情况
            DynamicCollect dynamicCollect = dynamicCollectMapper.selectOne(
                    new QueryWrapper<DynamicCollect>().eq("dynamic_id", dynamic.getId())
            );

            FrontendDynamicItemVO dynamicVO = initFrontendDynamicItemVO(dynamic, user, dynamicFileList, dynamicStar, dynamicCollect);
            dynamicVOList.add(dynamicVO);
        }

        PageVO<List<FrontendDynamicItemVO>> data = new PageVO<>();
        data.setTotal(total - 1);
        data.setCurrent(currentPage);
        data.setData(dynamicVOList);

        return R.success(data);
    }

    @Override
    public ResVO<Map<String, Object>> addDynamic(String token, DynamicVO dynamicVO) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        User user = userClient.selectById(userId);
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
        backAuditClient.insert(recordAudit);

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
        if (userClient.selectById(userId) == null) {
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

    private FrontendDynamicItemVO initFrontendDynamicItemVO(Dynamic dynamic, User user, List<DynamicFile> dynamicFileList,
                                                            DynamicStar dynamicStar, DynamicCollect dynamicCollect) {
        FrontendDynamicItemVO data = new FrontendDynamicItemVO();
        data.setId(dynamic.getId());
        data.setTitle(dynamic.getTitle());
        data.setCreateAt(dynamic.getCreateAt());
        data.setUpdateAt(dynamic.getUpdateAt());
        data.setContent(dynamic.getContent());
        data.setLikeCount(dynamic.getLikeCount());
        data.setCollectCount(dynamic.getCollectCount());
        data.setNickName(user.getNickName());
        data.setDynamicFileList(dynamicFileList);
        data.setAvatarUrl(user.getAvatarUrl());
        data.setCommentCount(dynamic.getCommentCount());
        data.setIsLike(dynamicStar != null);
        data.setIsCollect(dynamicCollect != null);
        return data;
    }

    private DynamicVO initDynamicVO(Dynamic dynamic, User user, List<DynamicFile> dynamicFileList,
                                    DynamicStar dynamicStar, DynamicCollect dynamicCollect) {
        DynamicVO data = new DynamicVO();
        data.setId(dynamic.getId());
        data.setTitle(dynamic.getTitle());
        data.setCreateAt(dynamic.getCreateAt());
        data.setUpdateAt(dynamic.getUpdateAt());
        data.setContent(dynamic.getContent());
        data.setLikeCount(dynamic.getLikeCount());
        data.setCollectCount(dynamic.getCollectCount());
        data.setNickName(user.getNickName());
        data.setDynamicFileList(dynamicFileList);
        data.setAvatarUrl(user.getAvatarUrl());
        data.setCommentCount(dynamic.getCommentCount());
        data.setIsLike(dynamicStar != null);
        data.setIsCollect(dynamicCollect != null);
        return data;
    }
}




