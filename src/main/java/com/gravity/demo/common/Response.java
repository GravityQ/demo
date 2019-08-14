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
public class Response<T> implements Serializable {

    private static final long serialVersionUID = -4827442398635070382L;
    private int code;
    private String msg;
    private T data;

    private Response() {
    }

    private Response(T data) {
        this.code = 0;
        this.msg = "SUCCESS";
        this.data = data;
    }

    private Response(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public static Response success() {
        return new Response(0, "SUCCESS");
    }

    public static <T> Response<T> success(Object data) {
        return new Response(data);
    }

    public static Response error() {
        return new Response(1, "ERROR");
    }

    public static Response error(String msg) {
        return new Response(1, msg);
    }

    public static Response error(int code, String msg) {
        return new Response(code, msg);
    }
}
