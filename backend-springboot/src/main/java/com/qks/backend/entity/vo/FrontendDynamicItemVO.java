package com.qks.backend.entity.vo;

import com.qks.backend.entity.po.DynamicFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrontendDynamicItemVO {
    private Long id;
    private Long recordId;
    private String nickName;
    // 用户头像
    private String avatarUrl;
    private String title;
    private Timestamp createAt;
    private Timestamp updateAt;
    private String content;
    private List<DynamicFile> dynamicFileList;
    private Long likeCount;
    private Long collectCount;
    private Long commentCount;
    private Boolean isLike;
    private Boolean isCollect;
    private Integer state;
    private String message;
}
