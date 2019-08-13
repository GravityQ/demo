package com.gravity.demo.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 权限类型枚举
 *
 * @author qijunlin
 * @date 2019-08-13 19:33
 */
public enum AuthTypeEnum implements IEnum {
    /**
     * 需要登录
     */
    LOGIN(1),
    /**
     * 开放,无需鉴权
     */
    OPEN(2),
    /**
     * 需要鉴定是否包含权限
     */
    AUTH(3);
    @EnumValue
    private final int value;

    AuthTypeEnum(final int value) {
        this.value = value;
    }

    /**
     * @return
     * @JsonValue 可以用在get方法或者属性字段上，一个类只能用一个，
     * 当加上@JsonValue注解是，序列化是只返回这一个字段的值。
     */
    @JsonValue
    @Override
    public int getValue() {
        return this.value;
    }
}
