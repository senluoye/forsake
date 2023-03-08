package com.qks.backend.entity.po.message;

import com.qks.backend.entity.po.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message extends Model {

    // 通知内容
    private String content;

    // 发布人id
    private Long userId;

}
