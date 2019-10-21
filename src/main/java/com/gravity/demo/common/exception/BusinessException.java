package com.gravity.demo.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务异常
 *
 * @author qijunlin
 * @date 2019-08-16 18:04
 */
@AllArgsConstructor
@Getter
public class BusinessException extends RuntimeException {
    private int code = 1;
    private String msg;

   public BusinessException(String msg) {
        this.msg = msg;
    }
}
