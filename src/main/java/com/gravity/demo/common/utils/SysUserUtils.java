package com.gravity.demo.common.utils;

import org.apache.shiro.SecurityUtils;

/**
 * 系统用户工具
 *
 * @author qijunlin
 * @date 2019-08-16 16:32
 */

public class SysUserUtils {

    public static String getLoginUsername() {
        return  (String) SecurityUtils.getSubject().getPrincipal();
    }

}
