package com.gravity.demo.common;


/**
 * 全局响应
 *
 * @author qijunlin
 * @date 2019-08-06 18:30
 */

public class Response<T> {

    private int code;
    private String msg;
    private T data;

    private Response() {
    }

    private Response(T data) {
        this.code = 0;
        this.data = data;
    }

    private Response(int code, String msg) {
        this.code = code;
        this.msg = msg;

    }

    public static Response success() {
        return new Response(0, "操作成功！");
    }

    public static<T> Response<T> success(Object data) {
        return new Response(data);
    }

    public static Response error(int code, String msg) {
        return new Response(code, msg);
    }
}
