package com.gravity.demo.common.exception;

import com.gravity.demo.common.ResultResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常通知
 *
 * @author qijunlin
 * @date 2019-08-16 18:05
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = BusinessException.class)
    public ResultResponse businessExceptionHandler(HttpServletRequest request, BusinessException e) {
        return ResultResponse.error(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(value = Exception.class)
    public ResultResponse exceptHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return ResultResponse.error(1, e.getMessage());
    }
}
