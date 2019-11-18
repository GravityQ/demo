package com.gravity.demo.controller.sys;


import com.gravity.demo.common.ResultResponse;
import com.gravity.demo.common.utils.SysUserUtils;
import com.gravity.demo.service.sys.MenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 菜单表 前端控制器
 * </p>
 *
 * @author gravity
 * @since 2019-08-08
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Resource
    private MenuService menuService;

    @GetMapping("menu")
    public ResultResponse menu() {
        return menuService.getUserMenus(SysUserUtils.getLoginUsername());
    }

}

