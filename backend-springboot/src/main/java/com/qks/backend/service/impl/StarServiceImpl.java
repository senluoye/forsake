package com.qks.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qks.backend.dao.*;
import com.qks.backend.entity.enums.FlagEnum;
import com.qks.backend.entity.enums.PageEnum;
import com.qks.backend.entity.po.Star;
import com.qks.backend.entity.po.User;
import com.qks.backend.entity.vo.PageVO;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.entity.vo.StarDynamicVO;
import com.qks.backend.service.StarService;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StarServiceImpl extends ServiceImpl<StarMapper, Star>
        implements StarService {

    @Resource
    private StarMapper starMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private DynamicMapper dynamicMapper;

    @Resource
    private CompetitionMapper competitionMapper;

    @Resource
    private WorkInfoMapper workInfoMapper;

    @Override
    public ResVO<PageVO<List<StarDynamicVO>>> getStarList(Long userId, Long currentPage) {
        Page<Star> starPage = new Page<>(currentPage, PageEnum.DefaultNum.getPageNum());
        starMapper.selectPage(starPage, new LambdaQueryWrapper<Star>().eq(Star::getUserId, userId));

        List<Star> starList = starPage.getRecords();
        long total = starPage.getTotal();
        List<StarDynamicVO> starDynamicVOList = new ArrayList<>();

        for (Star star : starList) {
            User user = userMapper.selectById(star.getUserId());
            Integer flag = star.getFlag();
            Long recordId = star.getRecordId();
            String content;
            if (flag.equals(FlagEnum.Dynamic.getFlag())) {
                content = dynamicMapper.selectById(recordId).getContent();
            } else if (flag.equals(FlagEnum.Competition.getFlag())) {
                content = competitionMapper.selectById(recordId).getContent();
            } else {
                content = workInfoMapper.selectById(recordId).getContent();
            }

            StarDynamicVO starDynamicVO = StarDynamicVO.builder()
                    .dynamicId(recordId)
                    .flag(flag)
                    .isStar(true)
                    .nickName(user.getNickName())
                    .avatarUrl(user.getAvatarUrl())
                    .content(content)
                    .build();
            starDynamicVOList.add(starDynamicVO);
        }

        PageVO<List<StarDynamicVO>> data = new PageVO<>();
        data.setData(starDynamicVOList);
        data.setTotal(total);
        data.setSize(PageEnum.DefaultNum.getPageNum());
        data.setCurrent(currentPage);

        return R.success(data);
    }
}
