package com.gravity.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;

/**
 * 状态码，全局1正常，0异常
 *
 * @author qijunlin
 * @date 2019-08-06 19:20
 */
@AllArgsConstructor
public enum StatusEnum implements IEnum {
    NORMAL(0), DISABLE(1);
    @EnumValue
    private int value;

    @JsonValue
    @Override
    public int getValue() {
        return this.value;
    }
}
