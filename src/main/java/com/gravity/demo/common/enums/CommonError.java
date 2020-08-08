package com.gravity.demo.common.enums;

import com.google.common.base.CaseFormat;
import lombok.Getter;
import lombok.val;

/**
 * @since 2019-11-21
 */
@Getter
public enum CommonError {

    /**
     * 成功
     */
    SUCCESS(200),

    /**
     * 拒绝访问
     */
    @Deprecated
    FORBIDDEN(403),

    /**
     * 访问的资源不存在
     */
    NOT_FOUND(404),

    /**
     * 系统错误
     */
    SYSTEM_ERROR(500),

    /**
     * 无效请求
     */
    ILLEGAL_REQUEST(501),

    /**
     * 无效参数
     */
    ILLEGAL_PARAM(502),

    /**
     * 请求参数过大
     */
    EXCEED_SIZE(503),

    /**
     * 缺少参数
     */
    PARAM_MISSING(504),

    /**
     * 频繁调用
     */
    FREQUENT_INVOKE(510),

    /**
     * 黑名单用户
     */
    BLACKLIST(511),

    /**
     * 拒绝匿名访问
     */
    @Deprecated
    UNAUTHORIZED(512),
    /**
     * 记录不存在
     */
    RECORD_NON_EXISTENT(513);

    private final String key;

    private final Integer value;

    private final String message;

    CommonError(int value) {
        this.value = value;
        val key = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_HYPHEN, name());

        this.key = "common." + key;
        this.message = key + "NOT_TRANSLATED";
    }
}
