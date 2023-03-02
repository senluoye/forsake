package com.qks.commen.helper;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVO<T> implements Serializable {
    private T data;
    private Integer code;
    private String msg;

    public ResultVO(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
