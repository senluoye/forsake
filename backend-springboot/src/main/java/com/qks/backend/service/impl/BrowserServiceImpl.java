package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.*;
import com.qks.backend.entity.enums.FlagEnum;
import com.qks.backend.entity.enums.PageEnum;
import com.qks.backend.entity.po.Competition;
import com.qks.backend.entity.po.Dynamic;
import com.qks.backend.entity.po.User;
import com.qks.backend.entity.po.WorkInfo;
import com.qks.backend.entity.po.browser.Browser;
import com.qks.backend.entity.vo.BrowserContentVO;
import com.qks.backend.entity.vo.BrowserVO;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.exception.ServiceException;
import com.qks.backend.service.BrowserService;
import com.qks.backend.utls.JwtUtil;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: BrowserServiceImpl
 * @Description
 * @Author: Administrator
 * @Version v1.0
 * @Create: 2023-03-08 15:54
 **/
@Service
@Transactional(rollbackFor = ServiceException.class)
public class BrowserServiceImpl extends ServiceImpl<BrowserMapper, Browser> implements BrowserService {

    @Resource
    private BrowserMapper browserMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DynamicMapper dynamicMapper;

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private WorkInfoMapper workInfoMapper;

    @Override
    public ResVO<Map<String, Object>> addBrowser(String token, BrowserVO browserVO) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        Integer flag = browserVO.getFlag();
        Long recordId = browserVO.getRecordId();

        if (userMapper.selectById(userId) == null) {
            throw new ServiceException("用户不存在");
        }

        checkRecordExist(recordId, flag);

        Browser browser = Browser.builder()
                .flag(flag)
                .userId(userId)
                .recordId(recordId)
                .build();
        if (browserMapper.insert(browser) < 1) {
            throw new ServiceException("添加浏览记录失败");
        }

        return R.map("browserId", browser.getId());
    }


    @Override
    public ResVO<Map<String, Object>> deleteBrowser(String token, Long browserId) throws ServiceException {
        Long userId = JwtUtil.getUserId(token);
        if (userMapper.selectById(userId) == null) {
            throw new ServiceException("用户不存在");
        }

        browserMapper.deleteById(browserId);

        return R.map("browserId", browserId);
    }

    @Override
    public ResVO<PageVO<List<BrowserContentVO>>> getBrowserList(String token, Long currentPage) {
        Page<Browser> browserPage = new Page<>(currentPage, PageEnum.DefaultNum.getPageNum());
        browserMapper.selectPage(browserPage, new LambdaQueryWrapper<>());

        List<Browser> browserList = browserPage.getRecords();
        Long total = browserPage.getTotal();
        List<BrowserContentVO> browserContentVOList = new ArrayList<>();

        for (Browser browser : browserList) {
            Long recordId = browser.getRecordId();
            Integer flag = browser.getFlag();

            String content;
            if (flag.equals(FlagEnum.Dynamic.getFlag())) {
                content = dynamicMapper.selectById(recordId).getContent();
            } else if (flag.equals(FlagEnum.Competition.getFlag())) {
                content = competitionMapper.selectById(recordId).getContent();
            } else {
                content = workInfoMapper.selectById(recordId).getContent();
            }

            User user = userMapper.selectById(browser.getUserId());

            BrowserContentVO browserContentVO = BrowserContentVO.builder()
                    .recordId(recordId)
                    .content(content)
                    .avatarUrl(user.getAvatarUrl())
                    .nickName(user.getNickName())
                    .flag(flag)
                    .isBowser(true)
                    .build();
            browserContentVOList.add(browserContentVO);
        }

        PageVO<List<BrowserContentVO>> data = new PageVO<>();
        data.setData(browserContentVOList);
        data.setSize(PageEnum.DefaultNum.getPageNum());
        data.setTotal(total);
        data.setCurrent(currentPage);
        return R.success(data);
    }

    private void checkRecordExist(Long recordId, Integer flag) throws ServiceException {
        if (flag.equals(FlagEnum.Dynamic.getFlag()) && dynamicMapper.exists(
                new LambdaQueryWrapper<Dynamic>().eq(Dynamic::getId, recordId)
        ) || flag.equals(FlagEnum.Competition.getFlag()) && competitionMapper.exists(
                new LambdaQueryWrapper<Competition>().eq(Competition::getId, recordId)
        ) || flag.equals(FlagEnum.Work.getFlag()) && workInfoMapper.exists(
                new LambdaQueryWrapper<WorkInfo>().eq(WorkInfo::getId, recordId)
        )) {
            throw new ServiceException("动态不存在");
        }
    }
}
