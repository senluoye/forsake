package com.qks.common.entity.vo;

import com.qks.common.entity.po.DynamicFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * @ClassName DynamicVO
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-22 18:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DynamicVO {
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
