package com.gravity.demo.common.enums;

import lombok.AllArgsConstructor;

/**
 * 状态码，全局1正常，0异常
 *
 * @author qijunlin
 * @date 2019-08-06 19:20
 */
@AllArgsConstructor
public enum StatusEnum {
    NORMAL(0),DISABLE(1);
    private int code;
}
