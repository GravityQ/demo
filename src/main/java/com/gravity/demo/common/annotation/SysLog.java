package com.gravity.demo.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author qijunlin
 * @date 2019/11/22 4:57 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String value() default "";
}
