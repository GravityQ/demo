package com.gravity.demo.params.sys;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 登录请求参数
 *
 * @author qijunlin
 * @date 2019-08-14 11:36
 */
@Data
public class LoginParam {
    @NotNull
    private String username;
    @NotNull
    @Length(min = 6)
    private String password;
}
