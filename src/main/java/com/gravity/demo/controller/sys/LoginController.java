package com.gravity.demo.controller.sys;

import com.gravity.demo.common.Response;
import com.gravity.demo.common.utils.IpUtils;
import com.gravity.demo.params.sys.LoginParam;
import com.gravity.demo.service.sys.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户登录相关
 *
 * @author qijunlin
 * @date 2019-08-14 11:31
 */
@RestController
@RequestMapping("/account")
public class LoginController {
    @Autowired
    private UserService userService;
    @PostMapping("/token")
    public Response login(@RequestBody @Validated LoginParam loginParam, HttpServletRequest request) {
       return userService.login(loginParam.getUsername(), loginParam.getPassword(), IpUtils.getIpAdress(request));
    }
}
