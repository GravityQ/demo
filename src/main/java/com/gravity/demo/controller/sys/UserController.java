package com.gravity.demo.controller.sys;


import com.gravity.demo.common.ResultResponse;
import com.gravity.demo.common.enums.StatusEnum;
import com.gravity.demo.entity.sys.User;
import com.gravity.demo.service.sys.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    public ResultResponse<User> list(@RequestParam String username, @RequestParam StatusEnum status) {
        List<User> list = userService.query().likeRight(StringUtils.isNotBlank(username), "username", username)
                .eq("status", status).list();
        return ResultResponse.success(list);
    }
}

