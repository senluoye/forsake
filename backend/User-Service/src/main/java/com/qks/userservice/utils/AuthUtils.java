package com.qks.userservice.utils;

import com.google.gson.Gson;
import com.qks.common.exception.ServiceException;
import com.qks.common.model.dto.WxAuthDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * @ClassName AuthUtils
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-24 16:17
 */
public class AuthUtils {

    private static final String secret = "685b3f0dd8cef0fa739581edda095474";

    private static final String appid = "wxa7e5d7d37d6fcdb8";

    public static WxAuthDTO auth(String code) throws ServiceException, IOException {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid
                + "&secret=" + secret
                + "&js_code=" + code
                + "&grant_type=authorization_code";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        ResponseBody responseBody = client.newCall(request).execute().body();

        if (responseBody == null) {
            throw new ServiceException("微信用户数据为空");
        }

        String result = responseBody.string();
        System.out.println(result);

        return new Gson().fromJson(result, WxAuthDTO.class);
    }
}
