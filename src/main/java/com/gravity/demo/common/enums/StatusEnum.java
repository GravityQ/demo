package com.gravity.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 状态码，全局1正常，0异常
 *
 * @author qijunlin
 * @date 2019-08-06 19:20
 */
@AllArgsConstructor

public enum StatusEnum {
    NORMAL(0), DISABLE(1);
    @EnumValue
    private final int value;

    @JsonValue
    public int getValue() {
        return value;
    }
}
