package com.qks.commen.handler;

import com.qks.commen.entity.vo.ResVO;
import com.qks.commen.exception.ServiceException;
import com.qks.commen.helper.R;
import io.jsonwebtoken.MalformedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName Dessert
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-19 17:29
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        logger.error("Time: " + df.format(new Date()));
        logger.error(req.getRequestURI());
        e.printStackTrace();

        return R.error(-1, e.getMessage());
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-d:HH:mm:ss");
        logger.error("Time: " + df.format(new Date()));
        logger.error(req.getRequestURI());
        e.printStackTrace();

        return R.error(-1, "空指针异常");
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
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        logger.error("Time: " + df.format(new Date()));
        logger.error(req.getRequestURI());
        e.printStackTrace();

        return R.error(-1, e.getMessage());
    }

    @ExceptionHandler(value = MalformedJwtException.class)
    @ResponseBody
    private ResVO<Map<String, Object>> malformedJwtExceptionHandler(HttpServletRequest req, MalformedJwtException e) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        logger.error("Time: " + df.format(new Date()));
        logger.error(req.getRequestURI());
        e.printStackTrace();

        return R.error(-1, "凭证不存在");
    }
}

