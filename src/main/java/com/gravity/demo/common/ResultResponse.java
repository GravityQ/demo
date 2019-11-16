package com.gravity.demo.common;


import lombok.Data;

import java.io.Serializable;

/**
 * 全局响应
 * 没有get，set方法不能转成json格式
 * @author qijunlin
 * @date 2019-08-06 18:30
 */
@Data
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = -4827442398635070382L;
    private int code;
    private String msg;
    private T data;


    private ResultResponse(T data) {
        this.code = 0;
        this.msg = "SUCCESS";
        this.data = data;
    }

    private ResultResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public static ResultResponse success() {
        return new ResultResponse(0, "SUCCESS");
    }

    public static <T> ResultResponse<T> success(Object data) {
        return new ResultResponse(data);
    }

    public static ResultResponse error() {
        return new ResultResponse(1, "ERROR");
    }

    public static ResultResponse error(String msg) {
        return new ResultResponse(1, msg);
    }

    public static ResultResponse error(int code, String msg) {
        return new ResultResponse(code, msg);
    }
}
