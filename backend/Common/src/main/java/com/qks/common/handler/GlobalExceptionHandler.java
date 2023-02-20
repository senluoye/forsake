package com.qks.common.handler;

import com.qks.common.exception.LoginException;
import com.qks.common.exception.ServiceException;
import com.qks.common.utils.Res;
import com.qks.common.model.vo.ResVO;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-08-05 18:42
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    private ResVO<Map<String, Object>> serviceExceptionHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        logger.error("最新的请求: " + df.format(new Date()));
        logger.error(req.getRequestURI());
        logger.error(String.valueOf(e));
        e.printStackTrace();

        return Res.error(-1, e.getMessage());
    }

    /**
     * 自定义异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = LoginException.class)
    @ResponseBody
    private ResVO<Map<String, Object>> loginExceptionHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        logger.error("最新的请求: " + df.format(new Date()));
        logger.error(req.getRequestURI());
        logger.error(String.valueOf(e));

        return Res.error(-3, e.getMessage());
    }

    /**
     * 空指针异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    private ResVO<Map<String, Object>> nullPointerExceptionHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-d:HH:mm:ss");
        logger.error("最新的请求: " + df.format(new Date()));
        logger.error(req.getRequestURI());
        logger.error(String.valueOf(e));

        return Res.error(-1, "空指针异常");
    }

    /**
     * 其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private ResVO<Map<String, Object>> exceptionHandler(HttpServletRequest req, Exception e) {
        e.printStackTrace();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        logger.error("最新的请求: " + df.format(new Date()));
        logger.error(req.getRequestURI());
        logger.error(String.valueOf(e));

        return Res.error(-1, e.getMessage());
    }

}

