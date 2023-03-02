package com.qks.commen.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 验证token，是否登录
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

    private final String[] ignoredURLs = {
            "/api/user/login",
            "/api/user/register",
            "/api/user/checkToken",
            "/api/commodity/list"
    };

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) {
        /**
        * @Description: 进入controller层之前拦截请求
        * @Params: [httpServletRequest, httpServletResponse, o]
        * @Return: boolean
        * @Author: 谢浚霖
        * @Date: 12/22/2021
        */
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String url = httpServletRequest.getRequestURI();
        String token = httpServletRequest.getHeader("token");
        logger.info("最新的请求: " + df.format(new Date()));
        logger.info(url + " " + token);
        for (String item : ignoredURLs) {
            if (item.equals(url)) return true;
        }
        return JWTUtils.verify(httpServletRequest.getHeader("token"));
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) {
        /**
        * @Description: 处理请求完成后视图渲染之前的处理操作
        * @Params: [httpServletRequest, httpServletResponse, o, modelAndView]
        * @Return: void
        * @Author: 谢浚霖
        * @Date: 12/22/2021
        */
        System.out.println("请求方法: " + o.toString());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) {
        /**
        * @Description: 视图渲染之后的操作
        * @Params: [httpServletRequest, httpServletResponse, o, e]
        * @Return: void
        * @Author: 谢浚霖
        * @Date: 12/22/2021
        */
    }
}

