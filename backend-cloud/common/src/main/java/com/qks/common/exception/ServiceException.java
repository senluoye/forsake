package com.qks.common.exception;

/**
 * @ClassName ServiceException
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-19 17:29
 */
public class ServiceException extends Exception {
    public ServiceException(String msg) {
        super(msg);
    }
}
