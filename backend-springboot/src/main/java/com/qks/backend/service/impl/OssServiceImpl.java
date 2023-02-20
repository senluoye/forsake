package com.qks.backend.service.impl;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.qks.backend.entity.vo.ResVO;
import com.qks.backend.service.OssService;
import com.qks.backend.utls.ConstantPropertiesUtil;
import com.qks.backend.utls.R;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName OssServiceImpl
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2023-01-27 21:00
 */
@Service
public class OssServiceImpl implements OssService {

    private static final String DYNAMIC = "dynamic/";
    private static final String COMPETITION = "competition/";
    private static final String WORK = "work/";

    //上传头像到oss
    public List<String> upload(List<MultipartFile> fileList, String url) {
        //工具类获取值：分别是：地域节点、id、秘钥、项目名称

        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        List<String> urlList = new ArrayList<>();

        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            for (MultipartFile file : fileList) {
                //获取上传文件输入流
                InputStream inputStream = file.getInputStream();

                //获取文件名称
                String fileName = file.getOriginalFilename();

                //1、在文件名称里面添加随机唯一值（因为如果上传文件名称相同的话，后面的问价会将前面的文件给覆盖了）
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");//因为生成后的值有横岗，我们就把它去除，不替换也可以，也没有错
                fileName = url + uuid + "-" + fileName;

                //调用oss方法实现上传
                //参数一：Bucket名称  参数二：上传到oss文件路径和文件名称  比如 /aa/bb/1.jpg 或者直接使用文件名称  参数三：上传文件的流
                ossClient.putObject(bucketName, fileName, inputStream);

                //需要把上传到阿里云路径返回    https://edu-guli-eric.oss-cn-beijing.aliyuncs.com/1.jpg
                String uploadUrl = "https://" + bucketName + "." + endpoint + "/" + fileName;
                urlList.add(uploadUrl);
            }

            //关闭OSSClient
            ossClient.shutdown();

            return urlList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResVO<List<String>> uploadDynamicFile(List<MultipartFile> file) {
        return R.success(upload(file, DYNAMIC));
    }

    @Override
    public ResVO<List<String>> uploadCompetitionFile(List<MultipartFile> file) {
        return R.success(upload(file, COMPETITION));
    }

    @Override
    public ResVO<List<String>> uploadWorkFile(List<MultipartFile> file) {
        return R.success(upload(file, WORK));
    }

    @Override
    public ResVO<Map<String, String>> policy() {

        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 填写Host地址，格式为https://bucketname.endpoint。
        String host = "https://" + bucketName + "." + endpoint;
        String dirName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        // 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下。
        String dir = "dynamic/";

        Map<String, String> respMap = null;
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            //生成签名
            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            respMap = new LinkedHashMap<>();
            respMap.put("accesskeyid", accessKeyId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

            System.out.println(respMap);
            ossClient.shutdown();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return R.success(respMap);
    }
}
