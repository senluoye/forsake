package com.qks.common.model.po.competition;

import com.qks.common.model.po.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @ClassName CompetitionInfo
 * @Description 竞赛信息
 * @Author QKS
 * @Version v1.0
 * @Create 2022-12-08 09:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompetitionInfo extends BaseModel {

    // 赛事名称
    private String name;

    // 赛事开始时间
    private Timestamp startTime;

    // 赛事结束时间
    private Timestamp endTime;

    // 赛事描述
    private String description;

    // 赛事封面
    private String faceImgId;

    // 举办单位
    private String organizer;

    // 发布者
    private Long publisherId;
}
