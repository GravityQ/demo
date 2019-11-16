package com.gravity.demo.common.utils;

import com.gravity.demo.entity.sys.User;
import com.gravity.demo.service.sys.UserService;
import org.apache.shiro.SecurityUtils;

import javax.annotation.Resource;

/**
 * 系统用户工具
 *
 * @author qijunlin
 * @date 2019-08-16 16:32
 */

public class SysUserUtils {
    @Resource
    private static UserService userService;

    public static User getLoginUser() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        return userService.query().eq("login_name", username).one();
    }

}
