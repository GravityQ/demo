package com.gravity.demo.req.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 登录请求参数
 *
 * @author qijunlin
 * @date 2019-08-14 11:36
 */
@Data
public class LoginReq {
    @NotNull
    @ApiModelProperty("用户名")
    private String username;
    /**
     * 密码
     */
    @NotNull
//    @Length(min = 6)
    @ApiModelProperty
    private String password;
}
