package com.gravity.demo.controller.sys;


import com.gravity.demo.common.BaseController;
import com.gravity.demo.common.Response;
import com.gravity.demo.entity.Menu;
import com.gravity.demo.service.MenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author gravity
 * @since 2019-08-06
 */
@RestController
@RequestMapping("/sys/menu")
public class MenuController extends BaseController {
    @Resource
    private MenuService menuService;
    @RequestMapping("list")
    public Response<List<Menu>> list() {
        return Response.success(menuService.list());
    }
}

