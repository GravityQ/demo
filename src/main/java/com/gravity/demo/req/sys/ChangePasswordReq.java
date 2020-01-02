package com.gravity.demo.req.sys;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 修改密码请求参数
 *
 * @author qijunlin
 * @date 2019/10/21 2:57 下午
 */
@Data
public class ChangePasswordReq {
    @NotBlank(message = "原密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
}
