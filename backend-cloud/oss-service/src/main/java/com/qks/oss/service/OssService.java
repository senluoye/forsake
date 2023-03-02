package com.qks.oss.service;

import com.qks.work.entity.vo.ResVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OssService
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-27 21:00
 */
public interface OssService {

    ResVO<List<String>> uploadDynamicFile(List<MultipartFile> file);

    ResVO<List<String>> uploadCompetitionFile(List<MultipartFile> file);

    ResVO<List<String>> uploadWorkFile(List<MultipartFile> file);

    ResVO<Map<String, String>> policy();
}
