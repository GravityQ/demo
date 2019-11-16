package com.gravity.demo.controller.sys;

import com.gravity.demo.common.ResultResponse;
import com.gravity.demo.common.utils.IpUtils;
import com.gravity.demo.common.utils.SysUserUtils;
import com.gravity.demo.entity.sys.User;
import com.gravity.demo.params.sys.ChangePasswordParam;
import com.gravity.demo.params.sys.LoginParam;
import com.gravity.demo.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户登录相关
 *
 * @author qijunlin
 * @date 2019-08-14 11:31
 */
@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private UserService userService;

    // TODO: 2019/10/21 验证码没做
    @PostMapping("login")
    public ResultResponse<String> login(@RequestBody @Valid LoginParam loginParam, HttpServletRequest request) {
        return userService.login(loginParam.getUsername(), loginParam.getPassword(), IpUtils.getIpAdress(request));
    }

    @PostMapping("password/change")
    public ResultResponse<Void> changePassword(@RequestBody @Valid ChangePasswordParam param) {
        userService.updatePassword(SysUserUtils.getLoginUser().getLoginName(), param.getOldPassword(), param.getNewPassword());
        return ResultResponse.success();
    }

    @GetMapping("info")
    public ResultResponse<User> info() {
        return userService.info(SysUserUtils.getLoginUser().getUid());
    }

    @GetMapping("notRole")
    public ResultResponse notRole() {
        return ResultResponse.error("没有权限");
    }

}
