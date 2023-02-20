package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.*;
import com.qks.backend.entity.enums.PageEnum;
import com.qks.backend.entity.enums.audit.AuditFlagEnum;
import com.qks.backend.entity.enums.audit.AuditStateEnum;
import com.qks.backend.entity.po.*;
import com.qks.backend.entity.vo.*;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.AuditService;
import com.qks.backend.utls.BackUserUtil;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName AdminServiceImpl
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-30 19:11
 */
@Service
@Transactional(rollbackFor = ServiceException.class)
public class AuditServiceImpl extends ServiceImpl<AuditMapper, RecordAudit>
        implements AuditService {

    @Resource
    private DynamicMapper dynamicMapper;

    @Resource
    private DynamicFileMapper dynamicFileMapper;

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private WorkInfoMapper workInfoMapper;

    @Resource
    private BackUserUtil backUserUtil;

    @Resource
    private RecordAuditMapper recordAuditMapper;

    @Resource
    private UserMapper userMapper;

    @Override
    public ResVO<PageVO<List<DynamicVO>>> getDynamicList(String token, SearchParams searchParams) throws ServiceException {
        if (!backUserUtil.isAdminOrAuditor(token)) {
            throw new ServiceException("用户不是运营人员");
        }

        // 查询审核表记录
        Page<RecordAudit> recordAuditPage = getRecordAuditPage(searchParams);

        List<RecordAudit> recordAuditList = recordAuditPage.getRecords();
        long total = recordAuditPage.getTotal();
        Integer currentPage = searchParams.getCurrentPage();

        if (currentPage != 0 && total <= currentPage * 20L) {
            throw new ServiceException("后面没有数据啦");
        }

        if (recordAuditList.size() == 0) {
            PageVO<List<DynamicVO>> pageVO = new PageVO<>(total, currentPage.longValue());
            return R.success(pageVO);
        }

        List<Dynamic> dynamicList = dynamicMapper.selectList(
                new LambdaQueryWrapper<Dynamic>().in(
                        Dynamic::getId,
                        recordAuditList.stream()
                                .map(RecordAudit::getRecordId)
                                .collect(Collectors.toList())
                )
        );
        List<DynamicVO> data = dynamicList.stream()
                .map(dynamic -> {
                    User user = userMapper.selectById(dynamic.getUserId());
                    List<DynamicFile> dynamicFileList = dynamicFileMapper.selectList(
                            new LambdaQueryWrapper<DynamicFile>().eq(DynamicFile::getDynamicId, dynamic.getId())
                    );

                    Long id = null;
                    Integer state = null;
                    for (RecordAudit recordAudit : recordAuditList) {
                        if (recordAudit.getRecordId().equals(dynamic.getId())) {
                            id = recordAudit.getId();
                            state = recordAudit.getState();
                            break;
                        }
                    }

                    return DynamicVO.builder()
                            .id(id)
                            .state(state)
                            .recordId(dynamic.getId())
                            .title(dynamic.getTitle())
                            .createAt(dynamic.getCreateAt())
                            .updateAt(dynamic.getUpdateAt())
                            .content(dynamic.getContent())
                            .likeCount(dynamic.getLikeCount())
                            .collectCount(dynamic.getCollectCount())
                            .nickName(user.getNickName())
                            .dynamicFileList(dynamicFileList)
                            .avatarUrl(user.getAvatarUrl())
                            .commentCount(dynamic.getCommentCount())
                            .build();
                })
                .collect(Collectors.toList());

        PageVO<List<DynamicVO>> pageVO = new PageVO<>();
        pageVO.setData(data);
        pageVO.setTotal(total);
        pageVO.setCurrent(currentPage.longValue());
        pageVO.setSize(PageEnum.DefaultNum.getPageNum());

        return R.success(pageVO);
    }

    @Override
    public ResVO<List<Competition>> getCompetitionList(String token, SearchParams searchParams) throws ServiceException {
        if (!backUserUtil.isAdminOrAuditor(token)) {
            throw new ServiceException("用户不是运营人员");
        }

        List<RecordAudit> recordAudits;
        Integer currentPage = searchParams.getCurrentPage();
        Page<RecordAudit> recordAuditPage = getRecordAuditPage(searchParams);
        recordAudits = recordAuditPage.getRecords();

        long total = recordAuditPage.getTotal();
        if (currentPage != 0 && total <= currentPage * 20L) {
            throw new ServiceException("后面没有数据啦");
        }

        List<Competition> data = competitionMapper.selectList(
                new LambdaQueryWrapper<Competition>().in(
                        Competition::getId,
                        recordAudits.stream()
                                .map(RecordAudit::getRecordId)
                                .collect(Collectors.toList())
                )
        );
        return R.success(data);
    }

    @Override
    public ResVO<List<WorkInfo>> getWorkInfoList(String token, SearchParams searchParams) throws ServiceException {
        if (!backUserUtil.isAdminOrAuditor(token)) {
            throw new ServiceException("用户不是运营人员");
        }

        List<RecordAudit> recordAudits;
        Integer currentPage = searchParams.getCurrentPage();
        Page<RecordAudit> recordAuditPage = getRecordAuditPage(searchParams);
        recordAudits = recordAuditPage.getRecords();

        long total = recordAuditPage.getTotal();
        if (currentPage != 0 && total <= currentPage * 20L) {
            throw new ServiceException("后面没有数据啦");
        }

        List<WorkInfo> data = workInfoMapper.selectList(
                new LambdaQueryWrapper<WorkInfo>().in(
                        WorkInfo::getId,
                        recordAudits.stream()
                                .map(RecordAudit::getRecordId)
                                .collect(Collectors.toList())
                )
        );
        return R.success(data);
    }

    @Override
    public ResVO<Map<String, Object>> auditDynamic(String token, RecordAuditVO recordAuditVO) throws ServiceException {
        if (!backUserUtil.isAdminOrAuditor(token)) {
            throw new ServiceException("用户不是运营人员");
        }

        RecordAudit recordAudit = recordAuditMapper.selectById(recordAuditVO.getId());
        if (recordAudit == null) {
            throw new ServiceException("数据不存在，审核失败");
        }

        if (!recordAudit.getFlag().equals(AuditFlagEnum.Dynamic.getFlag())) {
            throw new ServiceException("数据不存在");
        }

        recordAudit.setState(recordAuditVO.getState());
        recordAudit.setMessage(recordAuditVO.getMessage());
        recordAudit.setRecordId(recordAudit.getRecordId());
        recordAuditMapper.updateById(recordAudit);

        return R.map("recordAuditId", recordAudit.getId());
    }

    @Override
    public ResVO<Map<String, Object>> auditCompetition(String token, RecordAuditVO recordAuditVO) throws ServiceException {
        if (!backUserUtil.isAdminOrAuditor(token)) {
            throw new ServiceException("用户不是运营人员");
        }

        RecordAudit recordAudit = recordAuditMapper.selectById(recordAuditVO.getId());
        if (recordAudit == null) {
            throw new ServiceException("数据不存在，审核失败");
        }

        if (!recordAudit.getFlag().equals(AuditFlagEnum.Competition.getFlag())) {
            throw new ServiceException("数据不存在");
        }

        recordAudit.setState(recordAuditVO.getState());
        recordAudit.setMessage(recordAuditVO.getMessage());
        recordAudit.setRecordId(recordAudit.getRecordId());
        recordAuditMapper.updateById(recordAudit);

        return R.map("recordAuditId", recordAudit.getId());
    }

    @Override
    public ResVO<Map<String, Object>> auditWorkInfo(String token, RecordAuditVO recordAuditVO) throws ServiceException {
        if (!backUserUtil.isAdminOrAuditor(token)) {
            throw new ServiceException("用户不是运营人员");
        }

        RecordAudit recordAudit = recordAuditMapper.selectById(recordAuditVO.getId());
        if (recordAudit == null) {
            throw new ServiceException("数据不存在，审核失败");
        }

        if (!recordAudit.getFlag().equals(AuditFlagEnum.Work.getFlag())) {
            throw new ServiceException("数据不存在");
        }

        recordAudit.setState(recordAuditVO.getState());
        recordAudit.setMessage(recordAuditVO.getMessage());
        recordAudit.setRecordId(recordAudit.getRecordId());
        recordAuditMapper.updateById(recordAudit);

        return R.map("recordAuditId", recordAudit.getId());
    }

    /**
     * 根据参数获取对应的审核记录
     *
     * @param searchParams
     * @return
     */
    private Page<RecordAudit> getRecordAuditPage(SearchParams searchParams) {
        Integer currentPage = searchParams.getCurrentPage();
        Page<RecordAudit> recordAuditPage = new Page<>(currentPage, PageEnum.DefaultNum.getPageNum());
        LambdaQueryWrapper<RecordAudit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RecordAudit::getFlag, AuditFlagEnum.Dynamic.getFlag()).orderByDesc(RecordAudit::getCreateAt);
        switch (searchParams.getState()) {
            case -1: // 不通过的记录
                wrapper.eq(RecordAudit::getState, AuditStateEnum.NoPass.getState());
                break;
            case 1: // 通过的记录
                wrapper.eq(RecordAudit::getState, AuditStateEnum.Pass.getState());
                break;
            case -11: // 未审核的记录
                wrapper.eq(RecordAudit::getState, AuditStateEnum.UnCheck.getState());
                break;
            case 11: // 已审核的记录
                wrapper.in(RecordAudit::getState,
                        Arrays.asList(AuditStateEnum.Pass.getState(), AuditStateEnum.NoPass.getState()));
            case 0:
            default:
                break;
        }
        recordAuditMapper.selectPage(recordAuditPage, wrapper);

        return recordAuditPage;
    }
}
